package net.neonlotus.chungusadmin.repositories.parambundles;

import java.util.List;

public class CheckEventsParams {
    List<String> ids;

    public CheckEventsParams(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }
}