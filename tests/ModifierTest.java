import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ModifierTest {

    @BeforeEach
    void setUp() {
    }
    @Test
    void loadModifiersCorrectTest() {
        ArrayList<Modifier> modifiersOriginal = new ArrayList<>();
        String linea = "SangreCerca 1 2";
        String[] var = linea.split(" ");
        Modifier modifier = new Modifier(var);
        modifiersOriginal.add(modifier);
        linea = "LunaLlena 1 3";
        var = linea.split(" ");
        modifier = new Modifier(var);
        modifiersOriginal.add(modifier);
        linea = "Licantropos 1 2";
        var = linea.split(" ");
        modifier = new Modifier(var);
        modifiersOriginal.add(modifier);
        linea = "AjoCerca 0 2";
        var = linea.split(" ");
        modifier = new Modifier(var);
        modifiersOriginal.add(modifier);
        linea = "MetalCerca 0 2";
        var = linea.split(" ");
        modifier = new Modifier(var);
        modifiersOriginal.add(modifier);
        linea = "MalaVision 0 2";
        var = linea.split(" ");
        modifier = new Modifier(var);
        modifiersOriginal.add(modifier);

        Modifier modifier1 = new Modifier();
        ArrayList<Modifier> modifiersComplete = new ArrayList<>();
        try {
            modifiersComplete = (ArrayList<Modifier>) modifier1.loadModifiers("tests/storeFilesTests");
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(modifiersOriginal.size(),modifiersComplete.size());
    }

    @Test
    void loadModifiersNoFileTest() {
        Modifier modifier1 = new Modifier();
        ArrayList<Modifier> modifiersEmpty = new ArrayList<>();
        try {
            modifiersEmpty = (ArrayList<Modifier>) modifier1.loadModifiers("Files");
        } catch (FileNotFoundException e) {

        }
        assertEquals(0, modifiersEmpty.size());
    }
}