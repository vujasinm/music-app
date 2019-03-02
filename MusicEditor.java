package cs3500.music;

import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.GuiController;
import cs3500.music.model.MusicEditorImpl;
import cs3500.music.model.MusicEditorModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.View;
import cs3500.music.view.ViewCreator;

/**
 * main method to run the program
 * The first input is the file name, the second input is the type of view: "midi", "gui",
 * "console", "combined"
 */
public class MusicEditor {

    public static void main(String[] args) throws InterruptedException {
        if (args[0] == null || args[1] == null) {
            throw new IllegalArgumentException("Invalid arguments.");
        }

        String file = args[0];  // file to read from
        String type = args[1];  // view type 'console', 'midi', 'gui', or 'combined'

        CompositionBuilder<MusicEditorModel> builder = new MusicEditorImpl.Builder();
        MusicReader reader = new MusicReader();

        try {
            reader.parseFile(new FileReader(file), builder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MusicEditorModel model = builder.build();
        View view = ViewCreator.create(type, (MusicEditorImpl) model);

        GuiController controller = new GuiController(model, view);
    }
}