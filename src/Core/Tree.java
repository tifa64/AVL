package Core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by louay on 4/28/2017.
 */
public class Tree {
    private AVLNode<String> root = null;

    public Tree(){

    }

    public void insertWord(String word){
        if (root == null){
            root = new AVLNode<String>(word);
        } else {
            root.insert(word);
        }
    }

    public void insertWords(File file){
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                this.insertWord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteWord(String word) throws Exception {
        if (root != null){
            AVLNode<String> deletedNode = root.delete(word);
            if (deletedNode == null) {
                throw new Exception("Node not found");
            }
        } else {
            throw new Exception("Tree is empty");
        }
    }

    public void deleteWords(File file) throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            for (String line : lines) {
                this.deleteWord(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean searchWord(String word) throws Exception {
        if (root == null){
            throw new Exception("Tree is empty");
        } else {
            return root.search(word);
        }
    }

    public boolean[] searchWords(File file) throws Exception {
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getPath()));
            boolean[] found = new boolean[lines.size()];
            int i = 0;
            for (String line : lines) {
                found[i] = this.searchWord(line);
                i++;
            }
            return found;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
