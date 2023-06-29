/*
Подумать над структурой класса Ноутбук для магазина техники - выделить поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий фильтрации и выведет ноутбуки, отвечающие фильтру.
NoteBook notebook1 = new NoteBook
NoteBook notebook2 = new NoteBook
NoteBook notebook3 = new NoteBook
NoteBook notebook4 = new NoteBook
NoteBook notebook5 = new NoteBook

Например: “Введите цифру, соответствующую необходимому критерию:
1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет

Далее нужно запросить минимальные значения для указанных критериев - сохранить параметры фильтрации можно также в Map.

Отфильтровать ноутбуки их первоначального множества и вывести проходящие по условиям.

Класс сделать в отдельном файле

приветствие
Выбор параметра
выбор конкретнее
вывод подходящих
*/

package HomeWorks.hw6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import HomeWorks.hw6.Shop.Notebook;

// import HomeWorks.hw6.Shop.Notebook;

class Shop implements Cloneable {
    List<Notebook> notebookArrayList;

    static class Notebook{
        String brandname;
        int screenSizeInch;
        int displayRate;
        String processor;
        int ramVolume;
        String videocard;
        Integer hddVolume;
        String os;
        String color;

        public Notebook(String brandname, int screenSizeInch, int displayRate, String processor, int ramVolume, String videocard, Integer hddVolume, String os, String color) {
            this.brandname = brandname;
            this.screenSizeInch = screenSizeInch;
            this.displayRate = displayRate;
            this.processor = processor;
            this.ramVolume = ramVolume;
            this.videocard = videocard;
            this.hddVolume = hddVolume;
            this.os = os;
            this.color = color;
        }

        public Notebook(String brandname){
            this.brandname = brandname;
        }

        public Notebook(){

        }

        @Override
        public String toString() {
            return ""+brandname+" "+screenSizeInch+" "+displayRate+" "+processor+" "+ramVolume+" "+videocard+" "+hddVolume+" "+os+" "+color+"";
        }


        

    }

    // @Override
    // public Shop clone() {
    //     Shop shopCloned = (Shop) this.clone();
    //     Shop newShop = new Shop();
    //     for (Notebook notebook : shopCloned.notebookArrayList) {
    //         newShop.notebookArrayList.add(notebook);
    //     }
    //     return newShop;
    // }

    @Override
    public String toString() {
        StringBuilder line = new StringBuilder();
        // for (Notebook notebook : notebookArrayList) {
        //     line.append(notebook+"\n");
        // }
        try {
            for (Notebook notebook : notebookArrayList) {
                line.append(notebook+"\n");
            }
        } catch (Exception e) {
            return "Перечень ноутбуков пуст";
        }
        return line.toString();
    }

    public void printMarkdownTable() {
        String title = "brandname|screenSizeInch|displayRate|processor|ramVolume|videocard|hddVolume|os|color";
        String textAlign = ":----|:----|:----|:----|:----|:----|:----|:----|:----";
        String row = "";
        System.out.println(title);
        System.out.println(textAlign);

        for (Notebook notebook : this.notebookArrayList) {
            row = notebook.toString().replaceAll(" ", "|");
            System.out.println(row);
        }
    } 

    public List<Notebook> insertRandom(int quantity) {
        Notebook[] notebooksRnd = new Notebook[quantity];
        this.notebookArrayList = Arrays.asList(notebooksRnd);
        Random rnd = new Random();

        String[] brands = new String[] {"Apple", "Acer", "ASUS", "HUAWEI", "Lenovo", "Microsoft", "Xiaomi", "GIGABYTE", "MSI", "Samsung"};
        Integer[] screenSize = new Integer[] {10, 11, 12, 13, 14, 15, 16, 17, 18};
        Integer[] displayRate = new Integer[] {50, 60, 100, 120, 144, 240};
        String[] processor = new String[] {"Apple", "Intel", "AMD"};
        Integer[] ramVolume = new Integer[] {4, 8, 16, 32, 64, 128, 256};
        String[] videocard = new String[] {"Intel", "AMD", "NVIDIA"};
        Integer[] hddVolume = new Integer[] {1, 2, 4, 8, 16, 32};
        String[] os = new String[] {"MacOS", "Linux", "Windows", "DOS", "None"};
        String[] color = new String[] {"Red", "Yellow", "Green", "Blue", "Black", "White", "Silver", "Gold", "Grey"};

        for (int i = 0; i < quantity; i++) {
            notebooksRnd[i] = new Notebook(brands[rnd.nextInt(brands.length)], screenSize[rnd.nextInt(screenSize.length)], displayRate[rnd.nextInt(displayRate.length)], processor[rnd.nextInt(processor.length)], ramVolume[rnd.nextInt(ramVolume.length)], videocard[rnd.nextInt(videocard.length)], hddVolume[rnd.nextInt(hddVolume.length)], os[rnd.nextInt(os.length)], color[rnd.nextInt(color.length)]);
        }
        
        return this.notebookArrayList;
    }

    private List<Notebook> filterNotebooks(int filter) {
        List<Notebook> filtered = new ArrayList<>();
        Scanner inputData = new Scanner(System.in);
        if (filter == 1 || filter == 4 || filter == 6 || filter == 8 || filter == 9) {
            String searchingPart = "";
            System.out.printf("Какой критерий? - ");
            String search = inputData.nextLine();
            for (Notebook notebook : this.notebookArrayList) {
                searchingPart = notebook.toString().split(" ")[filter-1].trim();
                if (searchingPart.equalsIgnoreCase(search)) {
                    filtered.add(notebook);
                }
            }
        } else {
            System.out.printf("От какого значения - ");
            int searchFrom = inputData.nextInt();
            System.out.printf("До какого значения - ");
            int searchTill = inputData.nextInt();
            int searchingPart = -1;
            for (Notebook notebook : this.notebookArrayList) {
                searchingPart = Integer.parseInt(notebook.toString().split(" ")[filter-1].trim());
                if (searchingPart >= searchFrom && searchingPart <= searchTill) {
                    filtered.add(notebook);
                }
            }
        }
        return filtered;
    }

    public List<Notebook> filter() {
        // 1 brands
        // 2 screenSize
        // 3 displayRate
        // 4 processor
        // 5 ramVolume
        // 6 videocard
        // 7 hddVolume
        // 8 os
        // 9 color

        Shop filtered = new Shop();
        Scanner inputData = new Scanner(System.in);
        Message( "1. Бренд\n"+//
                            "2. Диагональ экрана\n"+//
                            "3. Частота Hz экрана\n"+//
                            "4. Процессор\n"+//
                            "5. Объем ОЗУ\n"+//
                            "6. Видеокарта\n"+//
                            "7. Объем HDD / SDD\n"+//
                            "8. Операционная система\n"+//
                            "9. Цвет");
        System.out.printf("Выберите категорию поиска: ");
        int filter = -1;
        try {
            filter = inputData.nextInt();
        } catch (Exception e) {
            System.out.println("Исключение");
        }
        if (1 <= filter && filter <= 9) {
            filtered.notebookArrayList = this.filterNotebooks(filter);
        } else {
            Message("Ошибка ввода!");
        }

        return filtered.notebookArrayList;
    }

    private static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    private static void Message(String text) {
        System.out.println(repeat(80, "="));
        System.out.println(text);
        System.out.println(repeat(80, "="));
    }

}

public class Task1 {
    public static void main(String[] args) throws Exception {

        MenuMain();

    }

    private static void MenuMain() {
        Scanner inputData = new Scanner(System.in);
        Shop shop = new Shop();
        shop.insertRandom(100);
        // Shop filtered = new Shop();
        int inputAction = -1;
        int countingFilter = 1;
        Map<String, Shop> filterLogs = new HashMap<>();
        String inputLogNum = "";

        System.out.println("Добро пожаловать!");
        while (true) {
            ActionsMenu();
            System.out.printf("Выберите действие: ");
            switch (inputAction = inputData.nextInt()) {
                case 1:
                    System.out.println(shop);
                    break;
                case 2:
                    shop.printMarkdownTable();
                    break;
                case 3:
                    Shop filtered = new Shop();
                    filtered.notebookArrayList = shop.filter();
                    filterLogs.put(Integer.toString(countingFilter++),filtered);
                    System.out.println(filtered);
                    break;    
                case 4:
                    if (filterLogs.size() != 0) {
                        
                        // System.out.println(repeat(80, "="));
                        // System.out.println("Доступные прошлые поиски по номера:");
                        // System.out.println(filterLogs.keySet());
                        // System.out.println("");
                        // System.out.println(repeat(80, "="));
                        // System.out.printf("Выберите номер - ");
                        // inputLogNum = inputData.next();
                        // System.out.println("Было выбранно:");

                        // System.out.println(filterLogs.get(inputLogNum));

                        System.out.println(logsJournal(filterLogs));
                    } else {
                        Message("Журнал пуст - не произвели ни одной фильтрации.");
                    }
                    break;
                case 5:
                    if (filterLogs.size() != 0) {
                        // System.out.println(repeat(80, "="));
                        // System.out.println("Доступные прошлые поиски по номера:");
                        // System.out.println(filterLogs.keySet());
                        // System.out.println("");
                        // System.out.println(repeat(80, "="));
                        // System.out.printf("Выберите номер - ");
                        // inputLogNum = inputData.next();

                        Shop againFiltered = logsJournal(filterLogs);
                        String keyToPutMap = "";
                        // System.out.println("Было выбранно:");
                        // System.out.println(againFiltered);

                        // againFiltered.notebookArrayList = againFiltered.filter();
                        // System.out.println(againFiltered);
                        // filterLogs.put(inputLogNum, againFiltered);
                        
                        for (String key : filterLogs.keySet()) {
                            if (filterLogs.get(key).equals(againFiltered)) {
                                keyToPutMap = key;
                                System.out.println(keyToPutMap);
                                System.out.println(key);
                            }
                        }

                        System.out.println("Было выбранно:");
                        System.out.println(againFiltered);

                        againFiltered.notebookArrayList = againFiltered.filter();
                        System.out.println(againFiltered);
                        filterLogs.put(keyToPutMap, againFiltered);

                    } else {
                        Message("Журнал пуст - не произвели ни одной фильтрации.");
                    } 
                    break;
                case 6:
                    if (filterLogs.size() != 0) {
                        // System.out.println(repeat(80, "="));
                        // System.out.println("Доступные прошлые поиски по номера:");
                        // System.out.println(filterLogs.keySet());
                        // System.out.println("");
                        // System.out.println(repeat(80, "="));
                        // System.out.printf("Выберите номер - ");
                        // inputLogNum = inputData.next();

                        // filterLogs.get(inputLogNum).printMarkdownTable();
                        logsJournal(filterLogs).printMarkdownTable();
                    } else {
                        Message("Журнал пуст - не произвели ни одной фильтрации.");
                    }
                    break;
                case 7:
                    inputData.close();
                    System.exit(0);
                default:
                    System.out.println("Ошибка!");
                    break;
            }
        }        
    }

    private static Shop logsJournal(Map<String, Shop> filterLogs) {
        Scanner inputData = new Scanner(System.in);

        System.out.println(repeat(80, "="));
        System.out.println("Доступные прошлые поиски по номера:");
        System.out.println(filterLogs.keySet());
        System.out.println("");
        System.out.println(repeat(80, "="));

        System.out.printf("Выберите номер - ");
        String inputLogNum = inputData.next();
        return filterLogs.get(inputLogNum);
    }

    private static void ActionsMenu() {
        Message("1. Вывести все ноутбуки\n"+
                "2. Вывести все в формате Markdown таблицы\n"+
                "3. Отфильтровать по критериям\n"+
                "4. Журнал прошлых поисков\n"+
                "5. Отфильтровать прошлый поиск\n"+
                "6. Вывести прошлый поиск\n   в формате Markdown таблицы\n"+
                "7. Выход");
    }

    private static String repeat(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }

    private static void Message(String text) {
        System.out.println(repeat(80, "="));
        System.out.println(text);
        System.out.println(repeat(80, "="));
    }
    
}
