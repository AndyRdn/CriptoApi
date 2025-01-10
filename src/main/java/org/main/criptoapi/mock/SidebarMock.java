package org.main.criptoapi.mock;

import org.main.criptoapi.webComponent.Menu;
import org.main.criptoapi.webComponent.Sidebar;

public class SidebarMock {
    public static final Sidebar DEFAULT_SIDEBAR = new Sidebar("bx bx-chair", "Mr Meuble") // logo + nom du projet
        .withLien("/home") // Lien lorsqu'on clique sur le logo
        .addMenu(
                // Menu: Besoin
                new Menu("Dashboard")
                        .withIcon("bx bx-briefcase-alt") // icon du menu: a voir sur: boxicons.com -> clique sur
                        // icon -> font -> copier la class
                        .addSubmenu(
                                new Menu("Acceuil") // Besoin (menu) -> Demande de Besoin (sous-menu du
                                        // menu besoin)
                                        .withIcon("bx bx-mail-send")
                                        .withLien("/dashborad/")
                        )

        ).addMenu(
                // Menu: Besoin
                new Menu("Profil")
                        .withIcon("bx bx-briefcase-alt") // icon du menu: a voir sur: boxicons.com -> clique sur
                        // icon -> font -> copier la class
                        .addSubmenu(
                                new Menu("Info") // Besoin (menu) -> Demande de Besoin (sous-menu du
                                        // menu besoin)
                                        .withIcon("bx bx-user")
                                        .withLien("/fond/donnee")
                        ).addSubmenu(
                            new Menu("Portfeuille") // Besoin (menu) -> Demande de Besoin (sous-menu du
                                    // menu besoin)
                                    .withIcon("bx bx-cart-alt")
                                    .withLien("/mvt-crypto/wallet")
                    )

        ).addMenu(
            // Menu: Besoin
            new Menu("Achats et ventes")
                    .withIcon("bx bx-briefcase-alt") // icon du menu: a voir sur: boxicons.com -> clique sur
                    // icon -> font -> copier la class
                    .addSubmenu(
                            new Menu("Tous les achats et ventes") // Besoin (menu) -> Demande de Besoin (sous-menu du
                                    // menu besoin)
                                    .withIcon("bx bx-spreadsheet")
                                    .withLien("/mvt-crypto/list")
                    )

        );


}
