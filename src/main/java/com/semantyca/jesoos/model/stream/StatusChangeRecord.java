package com.semantyca.jesoos.model.stream;

import com.semantyca.mixpla.model.cnst.StreamStatus;

import java.time.LocalDateTime;

public record StatusChangeRecord(LocalDateTime timestamp, StreamStatus oldStatus,
                                 StreamStatus newStatus) {
}
