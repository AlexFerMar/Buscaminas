package com.main;

import com.buscaminas.Tablero;
import com.data.DataMethods;
import com.data.User;
import libreriaProyecto.LiProyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class MainMenu extends JFrame implements ActionListener {

    private final String CARPETA = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "imagenes" + File.separator;


    private JLabel backgroundLabel;
    private JLabel title;
    private JButton play;
    private JButton logUser;
    private JButton score;
    private JButton exit;
    private User user = null;

    public MainMenu() {

        super("Buscaminas");
        this.setSize(360, 390);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);


        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(new ImageIcon(CARPETA + "background.jpg"));
        backgroundLabel.setBounds(0, 0, 360, 390);
        backgroundLabel.setVisible(true);

        title = new JLabel("Buscaminas");
        title.setBounds(110, 5, 140, 60);
        title.setForeground(Color.BLACK);
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVisible(true);

        play = new JButton("Jugar");
        play.setBackground(null);
        play.setBounds(120, 85, 120, 50);
        play.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        play.addActionListener(this);
        play.setVisible(true);

        score = new JButton("Puntuaciones");
        score.setBounds(120, 145, 120, 50);
        score.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        score.addActionListener(this);
        score.setVisible(true);

        logUser = new JButton("Usuario");
        logUser.setBounds(120, 205, 120, 50);
        logUser.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        logUser.addActionListener(this);
        logUser.setVisible(true);

        exit = new JButton("Salir");
        exit.setBounds(120, 265, 120, 50);
        exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
        exit.addActionListener(this);
        exit.setVisible(true);

        this.add(title);
        this.add(play);
        this.add(score);
        this.add(logUser);
        this.add(exit);
        this.add(backgroundLabel);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


    }


    /**
     * Crea el tablero del buscaminas
     */
    public void tableroMina() {

        int dimensiones = 20;

        int dificultad = 2;

        Tablero t = new Tablero(dimensiones, dificultad);

        t.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {

                if (t.isTerminada()) {

                    if (user != null) {
                        DataMethods.saveScore(user, t.getPuntuacion());
                    }

                    if (LiProyecto.confirmMessage(t, "Quieres volver a jugar?", "Otra partida?")) {

                        tableroMina();

                    } else MainMenu.super.setVisible(true);
                } else MainMenu.super.setVisible(true);
            }
        });

        super.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent action) {

        if (action.getSource() == play) {

            tableroMina();

        }

        if (action.getSource() == score) {


            int seleccion = LiProyecto.simpleSelector("Elige una opcion", "Puntuaciones",
                    new String[]{"Usuario", "Global"}, this, new ImageIcon(CARPETA + "trophy.png"));
            if (seleccion == 0)

                DataMethods.showUserScore(user, this);

            else if (seleccion == 1)

                DataMethods.showGlobalScore(this);
        }


        if (action.getSource() == logUser) {

            int eleccion;
            if (user != null) {
                eleccion = LiProyecto.simpleSelector("Usuario: " + user.getName() + "#" + user.getCode(),
                        "Usuario", new String[]{"Modificar credenciales", "Cerrar sesion", "Borrar usuario"},
                        this, new ImageIcon(CARPETA + "userIcon.png"));

                switch (eleccion) {

                    case 0 -> {

                        eleccion = LiProyecto.simpleSelector("Usuario: " + user.getName() + "#" + user.getCode(),
                                "Usuario", new String[]{"Cambiar Nombre", "Cambiar contraseña", "Cambiar nombre y contraseña"},
                                this, new ImageIcon(CARPETA + "userIcon.png"));

                        user = DataMethods.changeUserData(user, this, eleccion);

                    }

                    case 1 -> {

                        user = null;

                    }

                    case 2 -> {

                        if (JOptionPane.showConfirmDialog(this, "Estas seguro de esta operacion?",
                                "Borrar Usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                            user = DataMethods.deleteUser(user, this);

                    }

                    default -> {


                    }
                }
            } else {

                eleccion = LiProyecto.simpleSelector("Escoja opcion", "Usuario",
                        new String[]{"Iniciar Sesion", "Registrarse"}, this,
                        new ImageIcon(CARPETA + "userIcon.png"));


                if (eleccion == 0) {

                    user = DataMethods.login(LiProyecto.askString("Introduce el nombre de usuario completo (Ejemplo#0)",
                            this), LiProyecto.askString("Introduce la contraseña", this), this);

                } else if (eleccion == 1) {

                    user = DataMethods.register(LiProyecto.askString("Introduce un nombre de usuario",
                            this), LiProyecto.askString("Introduce una contraseña", this), this);
                }

            }
        }

        if (action.getSource() == exit) {

            System.exit(0);

        }

    }
}
