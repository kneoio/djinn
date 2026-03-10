package com.semantyca.djinn.messaging;

import com.semantyca.djinn.dto.queue.SongQueueMessageDTO;
import com.semantyca.mixpla.dto.queue.AddToQueueDTO;
import com.semantyca.mixpla.dto.queue.QueueMessageDTO;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class QueueSupplier {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueueSupplier.class);

    @Inject
    @Channel("queue-requests")
    Emitter<QueueMessageDTO> emitter;

    @Inject
    @Channel("queue-requests")
    Emitter<SongQueueMessageDTO> songEmitter;

    public Uni<Void> sendToQueue(String brandName, AddToQueueDTO dto, String uploadId) {
        QueueMessageDTO message = new QueueMessageDTO();
        message.setBrandName(brandName);
        message.setDto(dto);
        message.setUploadId(uploadId);

        return Uni.createFrom()
                .completionStage(emitter.send(message))
                .onItem().invoke(() -> LOGGER.info("Successfully sent message to queue - brand: {}, uploadId: {}", brandName, uploadId))
                .onFailure().invoke(throwable -> LOGGER.error("Failed to send message to queue - brand: {}, uploadId: {}, error: {}",
                        brandName, uploadId, throwable.getMessage(), throwable))
                .onFailure().recoverWithUni(throwable -> Uni.createFrom().failure(throwable));
    }

    public Uni<Void> sendSongsToQueue(String brandName, SongQueueMessageDTO message, String uploadId) {
        return Uni.createFrom()
                .completionStage(songEmitter.send(message))
                .onItem().invoke(() -> LOGGER.info("Successfully sent songs to queue - brand: {}, scene: {}, uploadId: {}", 
                        brandName, message.getSceneTitle(), uploadId))
                .onFailure().invoke(throwable -> LOGGER.error("Failed to send songs to queue - brand: {}, uploadId: {}, error: {}",
                        brandName, uploadId, throwable.getMessage(), throwable))
                .onFailure().recoverWithUni(throwable -> Uni.createFrom().failure(throwable));
    }
}
