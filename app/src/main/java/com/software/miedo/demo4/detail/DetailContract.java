package com.software.miedo.demo4.detail;

public class DetailContract {
    interface View {
        void setPresenter(Presenter presenter);

        void mostrarCarga();

        void mostrarError(String errorxd);

        void mostrarDatos();

        void notificarCambios();

    }

    interface Presenter {
        void setView(View view);
        void loadData();

    }
}
