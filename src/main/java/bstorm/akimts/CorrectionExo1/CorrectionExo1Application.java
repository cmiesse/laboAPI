package bstorm.akimts.CorrectionExo1;

import bstorm.akimts.CorrectionExo1.dto.SectionDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CorrectionExo1Application {

	public static void main(String[] args){
		ApplicationContext ctx = SpringApplication.run(CorrectionExo1Application.class, args);
//		SectionService service = ctx.getBean(SectionService.class);
//
//		SectionDTO dto = SectionDTO.builder()
//				.id(1111)
//				.name("Test course")
//				.delegateId(1)
//				.build();

		// INSERT - OK
//		try {
//			service.insert( dto );
//		} catch (ElementAlreadyExistsException e) {
//			System.out.println(e.getMessage());
//		}

		// GETONE - OK
//		try {
//			System.out.println(service.getOne(1171));
//		} catch (ElementNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		// GETALL - OK
//		service.getAll().forEach(System.out::println);

		// DELETE - OK
//		try {
//			service.delete(1111);
//		} catch (ElementNotFoundException e) {
//			System.out.println(e.getMessage());
//		}

		// UPDATE
//		try {
//			service.update(dto, 5555);
//		} catch (ElementNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
	}

}
