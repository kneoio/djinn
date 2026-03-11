package com.semantyca.jesoos.llm;

import io.smallrye.mutiny.Uni;

public interface LlmClient {
    Uni<String> invoke(String prompt, String llmType);
}
