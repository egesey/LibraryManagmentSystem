package LibraryManagementSystem;

import java.util.Scanner;

public class LibraryTreeApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryTree libraryTree = new LibraryTree();

        
        
        libraryTree.loadFromFile();

        while (true) {
            System.out.println("\n📚 Kütüphane Yönetim Sistemi");
            System.out.println("1️⃣ Kitap Ekle");
            System.out.println("2️⃣ Kitap Sil");
            System.out.println("3️⃣ Kitap Bul");
            System.out.println("4️⃣ Kütüphane Ağacını Göster");
            System.out.println("0️⃣ Çıkış");
            System.out.print("Seçiminizi yapın: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            switch (choice) {
                case 1 -> {
                    System.out.print("Hangi kategoriye eklemek istiyorsunuz? (Completed Books, Recommended Books, Current Book): ");
                    String category = scanner.nextLine();
                    System.out.print("Kitap adını girin: ");
                    String bookName = scanner.nextLine();
                    System.out.print("Yazar adını girin: ");
                    String author = scanner.nextLine();
                    libraryTree.addBook(category, bookName, author);
                }
                case 2 -> {
                    System.out.print("Silmek istediğiniz kitabın adını girin: ");
                    String bookName = scanner.nextLine();
                    libraryTree.removeBook(bookName);
                }
                case 3 -> {
                    System.out.print("Bulmak istediğiniz kitabın adını girin: ");
                    String bookName = scanner.nextLine();
                    libraryTree.findBookPath(bookName);
                }
                case 4 -> libraryTree.displayTree();
                case 0 -> {
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }
}


