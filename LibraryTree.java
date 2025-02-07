package LibraryManagementSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class LibraryTree {
    private TreeNode root;

    public LibraryTree() {
        root = new TreeNode("Root");
    }

    public void loadFromFile() {
        File file = new File("/Users/egeseymen/Desktop/books.txt");
        if (!file.exists()) {
            System.err.println("Hata: Dosya bulunamadı! Lütfen yolu kontrol et: " + "/Users/egeseymen/Desktop/books.txt");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            TreeNode currentCategory = null;
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.equals("\\completedBooks")) {
                    currentCategory = new TreeNode("Completed Books");
                    root.addChild(currentCategory);
                }
                else  if (line.equals("\\recomendedBooks")) {
                    currentCategory = new TreeNode("Recommended Books");
                    root.addChild(currentCategory);
                }
                 else if (line.equals("\\currentBook")) {
                    currentCategory = new TreeNode("Current Book");
                    root.addChild(currentCategory);
                } else if (line.contains("##") && currentCategory != null) {
                    String[] parts = line.split("##");
                    if (parts.length == 2) {
                        TreeNode bookNode = new TreeNode(parts[0] + " - " + parts[1]);
                        currentCategory.addChild(bookNode);
                    } else {
                        System.err.println("Hatalı satır formatı: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }
    
    

    public void addBook(String category, String bookName, String author) {
        TreeNode categoryNode = root.findChild(category);
        if (categoryNode == null) {
            System.out.println("Kategori bulunamadı: " + category);
            return;
        }
        categoryNode.addChild(new TreeNode(bookName + " - " + author));
        System.out.println("Kitap eklendi: " + bookName);
    }

    public void removeBook(String bookName) {
        for (TreeNode category : root.children) {
            if (category.removeChild(bookName)) {
                System.out.println("Kitap silindi: " + bookName);
                return;
            }
        }
        System.out.println("Kitap bulunamadı: " + bookName);
    }

    public void findBookPath(String bookName) {
        List<String> path = new ArrayList<>();
        if (root.findPath(bookName, path)) {
            System.out.println("Kitaba giden yol: " + String.join(" -> ", path));
        } else {
            System.out.println("Kitap bulunamadı: " + bookName);
        }
    }

    public void displayTree() {
        root.display(0);
    }

}

