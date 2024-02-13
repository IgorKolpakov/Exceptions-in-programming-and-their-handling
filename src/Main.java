import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystemException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        String firstName;
        String lastName;
        String middleName;
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
        Date birthDate;
        String phoneNumber;
        char gender;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите данные в следующем порядке, разделенные пробелом: Фамилия, Имя, Отчество, дата рождения(dd.mm.yyyy), номер телефона, пол(символ латиницей f или m)");

        // Считываем введенные данные
        String input = scanner.nextLine();
        String[] inputData = input.split("\\s+");

        if (inputData.length != 6) {
            System.out.println("Ошибка: неверное количество данных");
            return;
        }

        firstName = inputData[0];
        lastName = inputData[1];
        middleName = inputData[2];

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.mm.yyyy");
            birthDate = dateFormat.parse(inputData[3]);
        } catch (ParseException e) {
            System.out.println("Ошибка: неверный формат даты");
            return;
        }

        try {
            phoneNumber = inputData[4];
            Long.parseLong(phoneNumber);
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: неверный формат номера телефона");
            return;
        }

        gender = inputData[5].charAt(0);
        if (gender != 'f' && gender != 'm') {
            System.out.println("Ошибка: неверный формат пола");
            return;
        }

                String fileName = "files\\" + firstName.toLowerCase() + ".txt";
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, true)){
            if (file.length() > 0){
                fileWriter.write('\n');
            }
            fileWriter.write(String.format("%s %s %s %s %s %s", firstName, lastName, middleName, format.format(birthDate), phoneNumber, gender));
        }catch (IOException e){
            throw new FileSystemException("Возникла ошибка при работе с файлом");
        }

        scanner.close();
    }
}