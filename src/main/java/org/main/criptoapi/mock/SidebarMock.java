package org.main.criptoapi.mock;

import org.main.criptoapi.webComponent.Menu;
import org.main.criptoapi.webComponent.Sidebar;

public class SidebarMock {
    public static final Sidebar DEFAULT_SIDEBAR = new Sidebar("bx bx-chair", "Mr Meuble") // logo + nom du projet
        .withLien("/home") // Lien lorsqu'on clique sur le logo
        .addMenu(
            // Menu: Besoin
            new Menu("Besoins")
                .withIcon("bx bx-briefcase-alt") // icon du menu: a voir sur: boxicons.com -> clique sur
                // icon -> font -> copier la class
                .addSubmenu(
                    new Menu("Demande de besoin") // Besoin (menu) -> Demande de Besoin (sous-menu du
                        // menu besoin)
                        .withIcon("bx bx-mail-send")
                        .withLien("/besoin/form")
                )
                .addSubmenu(
                    new Menu("Validation Besoin") // Besoin -> Validation Besoin
                        .withIcon("bx bx-task")
                        .withLien("/besoin/list")
                )

        )
        .addMenu(
                // Menu: Besoin
                new Menu("Profil")
                        .withIcon("bx bx-briefcase-alt") // icon du menu: a voir sur: boxicons.com -> clique sur
                        // icon -> font -> copier la class
                        .addSubmenu(
                                new Menu("Info") // Besoin (menu) -> Demande de Besoin (sous-menu du
                                        // menu besoin)
                                        .withIcon("bx bx-mail-send")
                                        .withLien("/fond/donnee")
                        )

        );


}
