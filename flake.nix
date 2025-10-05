{
  description = "Nix DevShell for DM_Kasse_5 JavaFX POS System";

  inputs = {
    nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.05";
  };

  outputs = { self, nixpkgs }: {
    devShells.x86_64-linux.default = let
      pkgs = import nixpkgs { system = "x86_64-linux"; };
    in pkgs.mkShell {
      name = "dm-kasse5-devshell";

      buildInputs = with pkgs; [
        ## --- Core runtime ---
        openjdk21
        maven
        sqlite
        fontconfig

        ## --- JavaFX native deps ---
        gtk3
        glib
        gsettings-desktop-schemas
        cairo
        pango
        gdk-pixbuf
        at-spi2-core
        at-spi2-atk
        mesa
        xorg.libX11
        xorg.libXtst
        xorg.libXxf86vm
        xorg.libXrender
        xorg.libXext
        xorg.libXi
        xorg.libXrandr
        xorg.xorgproto
        alsa-lib

        ## --- Optional utilities ---
        git
        zip
        unzip
        which
        gnumake
        bashInteractive

        ## --- Debug / inspection tools ---
        gdb
        lsof
        strace
        file
        jdk21_headless
      ];

      ## --- Environment variables ---
      env = {
        JAVA_HOME = "${pkgs.openjdk21}";
        PATH = "${pkgs.openjdk21}/bin:$PATH";
        MAVEN_OPTS = "-Xmx2G -Djava.library.path=${pkgs.gtk3}/lib:${pkgs.mesa}/lib:${pkgs.xorg.libX11}/lib";
      };

      shellHook = ''
        echo "────────────────────────────────────────────"
        echo "✅ DM_Kasse_5 DevShell ready!"
        echo ""
        echo " JavaFX + SQLite + GTK + Mesa configured"
        echo " Run app with: mvn clean javafx:run"
        echo "────────────────────────────────────────────"
      '';
    };
  };
}
