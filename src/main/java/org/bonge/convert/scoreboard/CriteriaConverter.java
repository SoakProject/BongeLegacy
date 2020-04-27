package org.bonge.convert.scoreboard;

import org.bonge.convert.Converter;
import org.spongepowered.api.scoreboard.critieria.Criteria;
import org.spongepowered.api.scoreboard.critieria.Criterion;

public class CriteriaConverter implements Converter<String, Criterion> {
    @Override
    public Class<Criterion> getSpongeClass() {
        return Criterion.class;
    }

    @Override
    public Class<String> getBukkitClass() {
        return String.class;
    }

    @Override
    public Criterion from(String criteria) {
        switch (criteria){
            case "dummy": return Criteria.DUMMY;
            case "deathCount": return Criteria.DEATHS;
            case "playerKillCount": return Criteria.PLAYER_KILLS;
            case "totalKillCount": return Criteria.TOTAL_KILLS;
            case "health": return Criteria.HEALTH;
            case "trigger": return Criteria.TRIGGER;
            default: throw new IllegalStateException("Unknown criteria of '" + criteria + "'");
        }
    }

    @Override
    public String to(Criterion criterion) {
        if(criterion.equals(Criteria.DEATHS)){
            return "deathCount";
        }
        if(criterion.equals(Criteria.DUMMY)){
            return "dummy";
        }
        if(criterion.equals(Criteria.HEALTH)){
            return "health";
        }
        if(criterion.equals(Criteria.PLAYER_KILLS)){
            return "playerKillCount";
        }
        if(criterion.equals(Criteria.TOTAL_KILLS)){
            return "totalKillCount";
        }
        if(criterion.equals(Criteria.TRIGGER)){
            return "trigger";
        }
        throw new IllegalStateException("Unknown criteria of " + criterion.getId());
    }
}
