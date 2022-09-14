package com.company.spacetrans.listener;

import com.company.spacetrans.entity.Spaceport;
import io.jmix.core.DataManager;
import io.jmix.core.event.EntityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("st_SpaceportEventListener")
public class SpaceportEventListener {

    @Autowired
    private DataManager dataManager;

    @EventListener
    public void onSpaceportChangedBeforeCommit(EntityChangedEvent<Spaceport> event) {
        Spaceport spaceport = dataManager.load(event.getEntityId()).one();
        if (spaceport.getIsDefault())
        {
            List<Spaceport> spaceports = dataManager.load(Spaceport.class)
                    .query("select c from st_Spaceport c where isDefault = true ").list();
            for (Spaceport spaceportChange : spaceports)
            {
                spaceportChange.setIsDefault(false);
            }
            dataManager.save(spaceports);
        }
        dataManager.save(spaceport);
    }
    
}