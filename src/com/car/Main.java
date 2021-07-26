package com.car;

import com.car.menu.CarBrand;
import com.car.menu.MenuItem;
import com.car.menu.SortMode;
import com.car.model.BMW;
import com.car.model.Car;
import com.car.model.Mercedes;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    static  Scanner scanner = new Scanner(System.in);
    static Map<CarBrand,List<Car>> map = new HashMap<>();
    static {
        map.put(CarBrand.BMW, new ArrayList<>());
        map.put(CarBrand.MERCEDES,new ArrayList<>());
    }


    public static void main(String[] args) {
          menu();

    }

    /**
     * main menu
     */
    public static  void menu()
    {
        MenuItem item = chooseMenu();
        switch (item){
            case ADD:addCar(); break;
            case SHOW:{
                CarBrand type = chooseBrand();
                showCar(type);
                break;
            }
            case REMOVE:  removeCar();break;
            case SORT:{
                sort();
               break;
            }
            case EXIT: System.exit(0); break;
            default: menu();break;
        }
        menu();
    }

    /**
     * menu operation
     * @return menu type
     */
    public  static MenuItem chooseMenu(){
        System.out.println("Menu:");
        int i=0;
        for(MenuItem item:MenuItem.values()){
            i++;
            System.out.printf("%d) %s\n",i,item);
        }
        System.out.print("Your choice: ");
        String str = scanner.nextLine();
        MenuItem item = null;

        try {
              item = MenuItem.valueOf(str.toUpperCase());
              return  item;
        }catch (Exception e){}
        // if the input is not correct, check the index
        try {
                i = Integer.parseInt(str);
                i-=1;
                if (i>=0 && i<MenuItem.values().length){
                    return MenuItem.values()[i];
                }
        }catch (Exception e){

        }
        chooseMenu();
        return item;
    }

    /**
     * brand entry menu
     * @return brand
     */
    public static CarBrand chooseBrand(){
        System.out.print("Please enter a car brand: ");
        System.out.print(Arrays.toString(CarBrand.values()));
        String choose = scanner.nextLine();
        CarBrand type = null;
        try {
            type = CarBrand.valueOf(choose.toUpperCase());
        }catch (Exception e){
                // if the input is not correct, we will repeat
                return  chooseBrand();
        }

        return type;
    }

    /**
     * sort mode selection menu
     * @return sort mode
     */
    public static SortMode chooseSort(){
        System.out.print("Please, input sort mode: ");
        System.out.print(Arrays.toString(SortMode.values()));
        String choose = scanner.nextLine();
        SortMode type = null;
        try {
            type = SortMode.valueOf(choose.toUpperCase());
        }catch (Exception e){
            // if the input is not correct, we will repeat
            return chooseSort();
        }
        return type;
    }


    /**
     * print cars
     * @param type car brand
     */
    public static void showCar(CarBrand type){
        List<Car> cars = map.get(type);
        if (cars.isEmpty()){
            System.out.println("The list is empty!");
            return;
        }
        cars.stream().forEach(car->{
            System.out.println(car);
        });
    }

    /**
     * remove car
     */
    public static void removeCar(){
        CarBrand type = chooseBrand();
        if (type==null) return;
        Car car = type == CarBrand.MERCEDES ? createMercedes():createBMW();
        if (car==null) {
            System.out.println("Error! Car is null!");
            return;
        }
        List<Car> cars = map.get(type);
        boolean removed = cars.remove(car);
        if (removed) System.out.printf("Car %s removed\n",car.getBrand());
        else
          System.out.println("Error!");
    }

    /**
     * add new car to map
     */
    public static void addCar(){
        CarBrand type = chooseBrand();
        if (type==null) return;
        Car car = type== CarBrand.MERCEDES? createMercedes():createBMW();
        if (car==null) {
            System.out.println("Error! Car is null!");
            return;
        }
        List<Car> cars = map.get(type);
        cars.add(car);
    }

    /**
     * sort car list by sort mode in car map by car brand
     */
    private static void sort(){
        CarBrand type = chooseBrand();
        if (type==null) return;
        SortMode mode = chooseSort();
        if (mode==null) return;
        switch (type){
            case MERCEDES:sortMercedes(map.get(type),mode);break;
            case BMW:sortBmw(map.get(type),mode);
        }
    }

    /**
     * sort list car BMW brand
     * @param list cars
     * @param mode sort mode
     */
    private static void sortBmw(List<Car> list,SortMode mode){
         switch (mode){
             case MODEL:{
                 List<Car>   cars = list.stream().map(c->(BMW)c).sorted(Comparator.comparing(BMW::getModel)).collect(Collectors.toList());
                   list.clear();list.addAll(cars);
                 break;}
             case SPEED:{
                 List<Car>  cars = list.stream().map(c->(BMW)c).sorted(Comparator.comparing(BMW::getMaxSpeed)).collect(Collectors.toList());
                 list.clear();list.addAll(cars);
                 break;}
             case AMG:
                 System.out.println("Sorry, this method is not available for BMW");
                 break;
         }
    }

    /**
     * sort list car Mercedess brand
     * @param list cars
     * @param mode sort mode
     */
    private static void sortMercedes(List<Car> list,SortMode mode){
        switch (mode){
            case MODEL:{
                List<Car>   cars = list.stream().map(c->(Mercedes)c).sorted(Comparator.comparing(Mercedes::getModel)).collect(Collectors.toList());
                list.clear();list.addAll(cars);
                break;}
            case AMG:{
                List<Car>  cars = list.stream().map(c->(Mercedes)c).sorted(Comparator.comparing(Mercedes::isAMG)).collect(Collectors.toList());
                list.clear();list.addAll(cars);
                break;}
            case SPEED:
                System.out.println("Sorry, this method is not available for Mercedes");
                break;
        }
    }

    /**
     * create new BMW
     * @return car
     */
    private   static Car createBMW(){
        System.out.print("Input BMW model: ");
        String model = scanner.nextLine();

        System.out.print("input max speed: ");
        int maxSpeed = scanner.nextInt(); scanner.nextLine();
        Car car =  new BMW(model,maxSpeed);
        return  car;
    }
    /**
     * create new Mercedes
     * @return car
     */
    private   static Car createMercedes(){
        System.out.print("Input Mercedes model: ");
        String model = scanner.nextLine();
        System.out.print("input is AMG (yes or no):");
        String choose  = scanner.nextLine();
        boolean amg = choose.toLowerCase().equals("yes")? true:false;
        Car car =  new Mercedes(model,amg);
        return  car;
    }


}
