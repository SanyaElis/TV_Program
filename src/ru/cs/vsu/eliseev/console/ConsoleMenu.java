package ru.cs.vsu.eliseev.console;

import ru.cs.vsu.eliseev.commands.*;

import java.util.Scanner;

public class ConsoleMenu {
    private Scanner sc = new Scanner(System.in);
    private boolean RUN = true;
    private Command[] commands;

    public ConsoleMenu() {
        this.commands = new Command[]{
                AddChannel.getINSTANCE(),
                AddTVShow.getINSTANCE(),
                ChangeTVShowGenre.getINSTANCE(),
                PrintAllChannels.getINSTANCE(),
                PrintAllTVShow.getINSTANCE(),
                RemoveChannel.getINSTANCE(),
                RemoveTVProgram.getINSTANCE(),
                ChangeTVShowDescription.getINSTANCE(),
                ChangeReviewOfChannel.getINSTANCE(),
                ChangeNameOfChannel.getINSTANCE(),
                ChangeNumberOfChannel.getINSTANCE(),
                ChangeChannelForTVShow.getINSTANCE(),
        };

    }

    public void startMenu() {
        while (RUN) {
            System.out.println("Выберете команду");
            for (int i = 0; i < commands.length; i++) {
                System.out.println("(" + i + ")" + commands[i]);
            }
            System.out.println("(-1) for exit");
            int variant = Integer.parseInt(sc.next());
            if (variant == -1) {
                RUN = false;
                break;
            }
            if (variant < commands.length && variant >= 0) {
                try {
                    commands[variant].execute(sc);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
