package models.services;

import java.util.ArrayList;
import java.util.List;

/**
 * Repair service
 */
public final class RepairService {
    /**
     * Get repair service's services
     * @return services
     */
    public List<Service> getServices() {
        final ArrayList<Service> services = new ArrayList<>();

        services.add(new Service("Tuning keyboard instrument", 125));
        services.add(new Service("Changing guitar's pickups", 540));
        services.add(new Service("Tamping guitar's frets", 50));
        services.add(new Service("Setting guitar's strings' length", 65));

        return services;
    }
}
