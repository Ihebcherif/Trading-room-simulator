package tn.esprit.fundsphere.Services.FormationService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class IAService {
    @Value("${openai.api.key}")
    private String openaiApiKey;

    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    public String generateQuiz(String courseContent) {
        RestTemplate restTemplate = new RestTemplate();

        // Création des en-têtes de la requête
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openaiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Préparation du prompt pour la requête
        String prompt = "Generate 5 quiz questions based on the following course content:\n" + courseContent;

        // Utilisation de Map pour construire la requête JSON
        Map<String, Object> requestPayload = new HashMap<>();
        requestPayload.put("model", "gpt-3.5-turbo");
        requestPayload.put("temperature", 0.7);
        requestPayload.put("max_tokens", 200);

        // Messages pour le modèle OpenAI
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "You are a helpful assistant.");

        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", prompt);

        requestPayload.put("messages", new Object[] { systemMessage, userMessage });

        // Convertir la Map en JSON avec ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        String body = null;
        try {
            body = objectMapper.writeValueAsString(requestPayload);
        } catch (Exception e) {
            throw new RuntimeException("Error converting request body to JSON: " + e.getMessage());
        }

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(OPENAI_API_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return parseQuizQuestions(response.getBody());
            } else {
                throw new RuntimeException("Failed to generate quiz. Status code: " + response.getStatusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while calling OpenAI API: " + e.getMessage());
        }
    }

    private String parseQuizQuestions(String responseBody) {
        // Ici, tu peux parser la réponse JSON pour extraire les questions
        // Pour l'instant, on retourne simplement la réponse brute
        return responseBody;
    }
}

