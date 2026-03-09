package com.semantyca.djinn.model.stream;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Getter
public class StreamAgenda {
    private final LocalDateTime createdAt;
    private final List<LiveScene> liveScenes;
    @Setter
    private ZoneId timeZone;

    public StreamAgenda(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        this.liveScenes = new ArrayList<>();
    }

    public void addScene(LiveScene entry) {
        this.liveScenes.add(entry);
    }

    public int getTotalScenes() {
        return liveScenes.size();
    }

    public int getTotalSongs() {
        return liveScenes.stream()
                .mapToInt(s -> s.getSongs().size())
                .sum();
    }

    public LocalDateTime getEstimatedEndTime() {
        if (liveScenes.isEmpty()) {
            return createdAt;
        }
        return liveScenes.getLast().getScheduledEndTime();
    }
}
