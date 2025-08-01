package br.com.marcenaria.api_marcenaria.controller;

import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("imagem")
public class GeradorDeImagemController {

    private final OpenAiImageClient imageClient;

    public GeradorDeImagemController(OpenAiImageClient imageClient) {
        this.imageClient = imageClient;
    }

    @GetMapping
    public List<ImageGeneration> gerarImagem(@RequestParam String prompt) {
        var options = ImageOptionsBuilder.builder()
                .withHeight(620)
                .withWidth(620)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(prompt);
        ImageResponse response = imageClient.call(imagePrompt);
        return response.getResults(); // lista de imagens
    }
}
