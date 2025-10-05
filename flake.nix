{
  description = "JavaFX DevShell for DM_Kasse_5";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs/nixos-24.05";

  outputs = { self, nixpkgs }: {
    devShells.x86_64-linux.default = let
      pkgs = import nixpkgs { system = "x86_64-linux"; };
    in pkgs.mkShell {
      buildInputs = [
        # JDK
        pkgs.openjdk21

        # GTK / GDK stack
        pkgs.glib
        pkgs.gtk3
        pkgs.gdk-pixbuf
        pkgs.pango
        pkgs.cairo
        pkgs.freetype
        pkgs.fontconfig

        # X11 runtime libraries
        pkgs.xorg.libX11
        pkgs.xorg.libXext
        pkgs.xorg.libXtst.out
        pkgs.xorg.libXi
        pkgs.xorg.libXcursor
        pkgs.xorg.libXrandr
        pkgs.xorg.libXxf86vm.out
        pkgs.xorg.libXinerama

        # OpenGL / Mesa stack
        pkgs.mesa
        pkgs.libGL
        pkgs.libGLU

        # Themes/icons
        pkgs.hicolor-icon-theme
        pkgs.gnome.adwaita-icon-theme
      ];

      shellHook = ''
        echo "JavaFX dev shell ready (JDK 21 + GTK/X11/OpenGL libs)"
      '';
    };
  };
}
