package com.company.spacetrans.listener;

import com.company.spacetrans.entity.CustomerGrade;
import com.company.spacetrans.entity.Discounts;
import com.company.spacetrans.entity.Waybill;
import com.company.spacetrans.service.WaybillService;
import io.jmix.core.DataManager;
import io.jmix.core.Id;
import io.jmix.core.event.EntityChangedEvent;
import liquibase.repackaged.net.sf.jsqlparser.statement.select.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component("st_WaybillEventListener")
public class WaybillEventListener {

    @Autowired
    private DataManager dataManager;

    @Autowired
    private WaybillService waybillService;

    @EventListener
    public void onWaybillChangedBeforeCommit(EntityChangedEvent<Waybill> event) {
        Id<Waybill> id = event.getEntityId();
        Waybill waybill = dataManager.load(id).one();
        Discounts discounts = dataManager.load(Discounts.class)
                .query("select c from st_Discounts c where grade = :grade" )
                .parameter("grade",waybill.getShipper().getGrade())
                .one();
        waybill = waybillService.calculateTotalWeight(waybill);
        waybill = waybillService.calculateTotalCharge(waybill, discounts);
        dataManager.save(waybill);
    }

}