package com.example.site_pl_99.dto;

import lombok.RequiredArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO запроса для создания или обновления новости")
@RequiredArgsConstructor
public class NewsDtoRequest {
    @Schema(description = "Заголовок новости на русском языке", example = "Новая программа обучения")
    private String titleRu;

    @Schema(description = "Заголовок новости на кыргызском языке", example = "Жаңы окуу программасы")
    private String titleKg;

    @Schema(description = "Подзаголовок новости на русском языке", example = "Старт уже скоро")
    private String subTitleRu;

    @Schema(description = "Подзаголовок новости на кыргызском языке", example = "Башталышы жакын")
    private String subTitleKg;

    @Schema(description = "Полное описание новости на русском языке", example = "Подробное описание новости...")
    private String descriptionRu;

    @Schema(description = "Полное описание новости на кыргызском языке", example = "Жаңылыктын толук баяны...")
    private String descriptionKg;

    @Schema(description = "Миниатюрное изображение новости")
    private ImageDto imageSmall;

    @Schema(description = "Полноразмерное изображение новости")
    private ImageDto imageFull;

    @Schema(description = "Список дополнительных изображений")
    private List<ImageDto> imagesFile;

    @Schema(description = "Видео, связанное с новостью")
    private VideoDto VideoFileName;

    public String getTitleRu() {
        return titleRu;
    }

    public NewsDtoRequest setTitleRu(String titleRu) {
        this.titleRu = titleRu;
        return this;
    }

    public String getTitleKg() {
        return titleKg;
    }

    public NewsDtoRequest setTitleKg(String titleKg) {
        this.titleKg = titleKg;
        return this;
    }

    public String getSubTitleRu() {
        return subTitleRu;
    }

    public NewsDtoRequest setSubTitleRu(String subTitleRu) {
        this.subTitleRu = subTitleRu;
        return this;
    }

    public String getSubTitleKg() {
        return subTitleKg;
    }

    public NewsDtoRequest setSubTitleKg(String subTitleKg) {
        this.subTitleKg = subTitleKg;
        return this;
    }

    public String getDescriptionRu() {
        return descriptionRu;
    }

    public NewsDtoRequest setDescriptionRu(String descriptionRu) {
        this.descriptionRu = descriptionRu;
        return this;
    }

    public String getDescriptionKg() {
        return descriptionKg;
    }

    public NewsDtoRequest setDescriptionKg(String descriptionKg) {
        this.descriptionKg = descriptionKg;
        return this;
    }

    public ImageDto getImageSmall() {
        return imageSmall;
    }

    public NewsDtoRequest setImageSmall(ImageDto imageSmall) {
        this.imageSmall = imageSmall;
        return this;
    }

    public ImageDto getImageFull() {
        return imageFull;
    }

    public NewsDtoRequest setImageFull(ImageDto imageFull) {
        this.imageFull = imageFull;
        return this;
    }

    public List<ImageDto> getImagesFile() {
        return imagesFile;
    }

    public NewsDtoRequest setImagesFile(List<ImageDto> imagesFile) {
        this.imagesFile = imagesFile;
        return this;
    }

    public VideoDto getVideoFileName() {
        return VideoFileName;
    }

    public NewsDtoRequest setVideoFileName(VideoDto videoFileName) {
        VideoFileName = videoFileName;
        return this;
    }
}
