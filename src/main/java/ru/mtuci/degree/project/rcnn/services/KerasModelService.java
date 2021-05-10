package ru.mtuci.degree.project.rcnn.services;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException;
import org.nd4j.common.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class KerasModelService {

    private ComputationGraph model;

    public KerasModelService() {
        try {
            String modelPath = new ClassPathResource(
                    "mask_rcnn_balloon.h5").getFile().getPath();
            String modelJsonPath = new ClassPathResource(
                    "model.json").getFile().getPath();
            this.model = KerasModelImport.importKerasModelAndWeights(modelJsonPath, modelPath);
        } catch (InvalidKerasConfigurationException | IOException | UnsupportedKerasConfigurationException e) {
            log.error("Failed to load Keras Model", e);
        }
    }

    public void predict() {

    }

}
