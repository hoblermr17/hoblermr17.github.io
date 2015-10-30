package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Actor;
import model.IActorDAO;

/**
 * @author Matt Hobler
 * @version 20151009
 * 
 */
public class ActorDAO implements IActorDAO {

	protected String fileName = null;
	protected final List<Actor> myList;

	public ActorDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Actor actor) {
		myList.add(actor);
		writeList();
	}

	@Override
	public Actor retrieveRecordById(int id) {
		for (Actor actor : myList) {
			if (actor.getActId() == id) {
				return actor;
			}
		}
		return null;
	}

	@Override
	public List<Actor> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Actor updatedActor) {
		for (Actor actor : myList) {
			if (actor.getActId() == updatedActor.getActId()) {
				actor.setLastName(updatedActor.getLastName());
				actor.setFirstName(updatedActor.getFirstName());
				actor.setHomePhone(updatedActor.getHomePhone());
				actor.setSalary(updatedActor.getSalary());
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Actor actor : myList) {
			if (actor.getActId() == id) {
				myList.remove(actor);
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(Actor actor) {
		myList.remove(actor);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String last = data[1];
				String first = data[2];
				String homePhone = data[3];
				double salary = Double.parseDouble(data[4]);
				Actor actor = new Actor(id, last, first, homePhone, salary);
				myList.add(actor);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Actor actor : myList) {
				writer.write(String.format("%d,%s,%s,%s,%.2f\n", actor.getActId(), actor.getLastName(),
						actor.getFirstName(), actor.getHomePhone(), actor.getSalary()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Actor actor : myList) {
			sb.append(String.format("%5d : %s, %s, %s, %.2f\n", actor.getActId(), actor.getLastName(),
					actor.getFirstName(), actor.getHomePhone(), actor.getSalary()));
		}

		return sb.toString();
	}

	@Override
	public void createRecord1(Actor actor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actor retrieveRecordById1(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecord1(Actor updatedActor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecord1(Actor actor) {
		// TODO Auto-generated method stub
		
	}
}
