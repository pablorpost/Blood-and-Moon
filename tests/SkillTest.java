import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SkillTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void loadSkillsCorrectTest() {
        ArrayList<Skill> skillsOriginal = new ArrayList<>();
        String linea = "Disciplines 1 2";
        String[] var = linea.split(" ");
        Skill skill = new Skill(var);
        skillsOriginal.add(skill);
        linea = "Gift 2 3";
        var = linea.split(" ");
        skill = new Skill(var);
        skillsOriginal.add(skill);
        linea = "Talent 2 2";
        var = linea.split(" ");
        skill = new Skill(var);
        skillsOriginal.add(skill);

        Skill skill1 = new Skill();
        ArrayList<Skill> skillsComplete = new ArrayList<>();
        try {
            skillsComplete = (ArrayList<Skill>) skill1.loadSkills("tests/storeFilesTests");
        } catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        assertEquals(skillsOriginal.size(),skillsComplete.size());
    }

    @Test
    void loadSkillsNoFileTest() {
        Skill skill1 = new Skill();
        ArrayList<Skill> skillsEmpty = new ArrayList<>();
        try {
            skillsEmpty = (ArrayList<Skill>) skill1.loadSkills("Files");
        } catch (FileNotFoundException e) {

        }
        assertEquals(0, skillsEmpty.size());
    }
}