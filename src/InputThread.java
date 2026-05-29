import java.util.Scanner;

class InputThread extends Thread {
    private static final Scanner SCANNER = new Scanner(System.in);

    @Override
    public void run() {

        while (true) {
            String input = SCANNER.nextLine();
            if (input.length() != 1 || input.charAt(0) < '1' || input.charAt(0) > '3' || input.equals(" ")) {
                System.out.println("Вы ввели неверную цифру. Попробуйте снова...");
                continue;
            }
            int flag = Integer.parseInt(input);
            switch (flag) {
                case 1:
                    Simulation.setIsPaused(true);
                    break;
                case 2:
                    Simulation.setIsPaused(false);
                    break;
                case 3:
                    Simulation.setIsOngoing(false);
                    return;
            }
        }
    }
}
