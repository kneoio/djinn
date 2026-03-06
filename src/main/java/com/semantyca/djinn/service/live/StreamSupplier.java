package com.semantyca.djinn.service.live;

import com.semantyca.djinn.model.stream.PendingSongEntry;
import com.semantyca.mixpla.model.soundfragment.SoundFragment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public abstract class StreamSupplier {
    private static final Logger LOGGER = LoggerFactory.getLogger(StreamSupplier.class);

  
    protected List<SoundFragment> pickSongsFromScheduled(
            List<PendingSongEntry> scheduledSongs,
            Set<UUID> excludeIds
    ) {
        List<PendingSongEntry> available = scheduledSongs.stream()
                .filter(e -> !excludeIds.contains(e.getSoundFragment().getId()))
                .toList();

        if (available.isEmpty()) {
            return List.of();
        }

        int take = available.size() >= 2 && new Random().nextDouble() < 0.6 ? 2 : 1;
        //int take = 1;
        return available.stream()
                .limit(take)
                .map(PendingSongEntry::getSoundFragment)
                .toList();
    }


}
