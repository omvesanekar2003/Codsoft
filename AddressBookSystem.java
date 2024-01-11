import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Contact {

  private String name;
  private String phoneNumber;
  private String emailAddress;

  public Contact(String name, String phoneNumber, String emailAddress) {
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getName() {
    return name;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }
}

class AddressBook {

  private ArrayList<Contact> contacts;

  public AddressBook() {
    this.contacts = new ArrayList<>();
  }

  public void addContact(Contact contact) {
    contacts.add(contact);
  }

  public void removeContact(Contact contact) {
    contacts.remove(contact);
  }

  public Contact searchContact(String name) {
    for (Contact contact : contacts) {
      if (contact.getName().equalsIgnoreCase(name)) {
        return contact;
      }
    }
    return null;
  }

  public void displayAllContacts() {
    System.out.println("All Contacts:");
    for (Contact contact : contacts) {
      System.out.println("Name: " + contact.getName());
      System.out.println("Phone Number: " + contact.getPhoneNumber());
      System.out.println("Email Address: " + contact.getEmailAddress());
      System.out.println("--------------------");
    }
  }

  public void saveContactsToFile(String fileName) {
    try (
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream(fileName)
      )
    ) {
      oos.writeObject(contacts);
      System.out.println("Contacts saved to " + fileName);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
  public void loadContactsFromFile(String fileName) {
    try (
      ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(fileName)
      )
    ) {
      contacts = (ArrayList<Contact>) ois.readObject();
      System.out.println("Contacts loaded from " + fileName);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}

public class AddressBookSystem {

  public static void main(String[] args) {
    AddressBook addressBook = new AddressBook();
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Address Book System Menu:");
      System.out.println("1. Add a new contact");
      System.out.println("2. Remove a contact");
      System.out.println("3. Search for a contact");
      System.out.println("4. Display all contacts");
      System.out.println("5. Save contacts to file");
      System.out.println("6. Load contacts from file");
      System.out.println("7. Exit");

      System.out.print("Enter your choice: ");
      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          System.out.print("Enter name: ");
          String name = scanner.nextLine();
          System.out.print("Enter phone number: ");
          String phoneNumber = scanner.nextLine();
          System.out.print("Enter email address: ");
          String emailAddress = scanner.nextLine();

          Contact newContact = new Contact(name, phoneNumber, emailAddress);
          addressBook.addContact(newContact);
          System.out.println("Contact added successfully!");
          break;
        case 2:
          System.out.print("Enter the name of the contact to remove: ");
          String contactToRemove = scanner.nextLine();
          Contact contact = addressBook.searchContact(contactToRemove);
          if (contact != null) {
            addressBook.removeContact(contact);
            System.out.println("Contact removed successfully!");
          } else {
            System.out.println("Contact not found!");
          }
          break;
        case 3:
          System.out.print("Enter the name of the contact to search: ");
          String contactToSearch = scanner.nextLine();
          Contact foundContact = addressBook.searchContact(contactToSearch);
          if (foundContact != null) {
            System.out.println("Contact found:");
            System.out.println("Name: " + foundContact.getName());
            System.out.println(
              "Phone Number: " + foundContact.getPhoneNumber()
            );
            System.out.println(
              "Email Address: " + foundContact.getEmailAddress()
            );
          } else {
            System.out.println("Contact not found!");
          }
          break;
        case 4:
          addressBook.displayAllContacts();
          break;
        case 5:
          System.out.print("Enter the file name to save contacts: ");
          String saveFileName = scanner.nextLine();
          addressBook.saveContactsToFile(saveFileName);
          break;
        case 6:
          System.out.print("Enter the file name to load contacts: ");
          String loadFileName = scanner.nextLine();
          addressBook.loadContactsFromFile(loadFileName);
          break;
        case 7:
          System.out.println("Exiting Address Book System. Goodbye!");
          System.exit(0);
          break;
        default:
          System.out.println("Invalid choice. Please enter a valid option.");
      }
    }
  }
}
