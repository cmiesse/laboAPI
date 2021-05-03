package bstorm.akimts.CorrectionExo1.presentation;

import bstorm.akimts.CorrectionExo1.dto.SectionDTO;
import bstorm.akimts.CorrectionExo1.exceptions.ElementAlreadyExistsException;
import bstorm.akimts.CorrectionExo1.exceptions.ElementNotFoundException;
import bstorm.akimts.CorrectionExo1.service.CrudService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
@Profile("console")
public class InterfaceConsole implements InitializingBean {

    private final Scanner scanner;
    private final CrudService<SectionDTO, Integer> service;

    public InterfaceConsole(Scanner scanner, CrudService<SectionDTO, Integer> service) {
        this.scanner = scanner;
        this.service = service;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    private void start(){

        int choix = 0;
        while( choix != 6 ){
            showMenu();
            choix = getChoix();
            mapChoix(choix);
        }

    }

    private void showMenu(){
        System.out.println("MENU");
        System.out.println("1 - Get All");
        System.out.println("2 - Get One");
        System.out.println("3 - Insert");
        System.out.println("4 - Delete");
        System.out.println("5 - Update");
        System.out.println("6 - Quitter");
    }

    private int getChoix(){
        int choix = -1;
        System.out.println("Veuillez entrer votre choix : ");
        try {
            choix = scanner.nextInt();
        }
        catch (InputMismatchException e){
            scanner.nextLine();
        }
        return choix;
    }

    private void mapChoix(int choix){

        switch (choix){
            case 1:
                getAll();
                break;
            case 2:
                getOne();
                break;
            case 3:
                insert();
                break;
            case 4:
                delete();
                break;
            case 5:
                update();
                break;
            case 6:
                System.out.println("Au revoir!");
                break;
            default:
                System.out.println("Choix invalide");
        }

    }

    private void getOne(){
        System.out.println("--> ID :");
        try{
            int id = scanner.nextInt();
            System.out.println( service.getOne(id) );
        }
        catch (InputMismatchException e){
            System.out.println("entrée invalide");
            scanner.nextLine();
        }
        catch (ElementNotFoundException e){
            System.out.println( e.getMessage() );
        }
    }
    private void getAll(){
        service.getAll().forEach(System.out::println);
    }
    private void insert(){
        int id;
        String name;
        int delegateId;
        try{
            System.out.println("--> ID : ");
            id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("--> NAME : ");
            name = scanner.nextLine();
            System.out.println("--> DELEGATE_ID : ");
            delegateId = scanner.nextInt();
            scanner.nextLine();

            SectionDTO dto = SectionDTO.builder()
                    .id(id)
                    .name(name)
                    .delegateId(delegateId)
                    .build();

            service.insert(dto);
        }
        catch (InputMismatchException e){
            System.out.println("Entrée invalide");
            scanner.nextLine();
        }
        catch (ElementAlreadyExistsException e){
            System.out.println(e.getMessage());
        }

    }
    private void update(){

        int id;
        String name;
        int delegateId;
        try{
            System.out.println("--> ID : ");
            id = scanner.nextInt();
            scanner.nextLine();

            SectionDTO old = service.getOne(id);

            System.out.println("--> NAME ["+old.getName()+"] : ");
            name = scanner.nextLine();
            System.out.println("--> DELEGATE_ID ["+old.getDelegateId()+"] : ");
            delegateId = scanner.nextInt();

            SectionDTO dto = SectionDTO.builder()
                    .id(id)
                    .name(name)
                    .delegateId(delegateId)
                    .build();

            service.update(dto, id);
        }
        catch (InputMismatchException e){
            System.out.println("Entrée invalide");
            scanner.nextLine();
        }
        catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
    private void delete(){

        System.out.println("--> ID :");
        try{
            int id = scanner.nextInt();
            service.delete(id);
        }
        catch (InputMismatchException e){
            System.out.println("Entrée invalide");
            scanner.nextLine();
        }
        catch (ElementNotFoundException e){
            System.out.println(e.getMessage());
        }

    }
}
