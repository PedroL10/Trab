package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;


import entities.AbstractVehicle;
import entities.Carro;
import entities.Frota;
import entities.Rota;
import entities.Van;

public class App {

    public static DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static Frota frota = new Frota();
    public static AtomicInteger count=new AtomicInteger(0);
    public static TxtFile txtFile = new TxtFile();

    public static AbstractVehicle[] veiculos = {new Carro("Carro", "HB20", "AAA1234", 50, 50000.00, 0.0),
                                                new Van("Van", "Scania", "ABC2222", 60, 80000.00, 0.0),
                                                new Carro("Carro", "Golf","FOG2022",  50, 90000.00, 0.0)};

    public static Rota[] rotas= {new Rota(400.0, LocalDate.parse("05/10/2022", fmt1)),
                                    new Rota(300.0, LocalDate.parse("21/09/2022", fmt1)),
                                    new Rota(200.0, LocalDate.parse("22/09/2022", fmt1)),
                                    new Rota(500.0, LocalDate.parse("23/09/2022", fmt1)),
                                    new Rota(700.0, LocalDate.parse("24/09/2022", fmt1))};

    private static void adcionarVeiculosAFrota(){

        Arrays.stream(veiculos).forEach(frota::adicionarVeiculo);

    }

    private static void criarRotasEAdcionar(){

        Arrays.stream(veiculos).forEach(veiculo -> veiculo.adicionaRota(rotas[count.incrementAndGet()]));

    }

    private static void adcionarVeiculosNoTxt(){

        Arrays.stream(veiculos).forEach(veiculo -> {
            try {
                txtFile.escreverArquivoTxt(veiculo.toString());
            }

            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private static void	mostrarFrotaVeiculos(){

        Arrays.stream(veiculos).forEach(System.out::println);
        System.out.printf("%.2f%n",frota.quilometragemMediaDasRotas(frota.getVehicles()));

    }

    private static void lerEPrintarArquivoTxt() throws FileNotFoundException {

        ArrayList<String> linhasArquivo;
        linhasArquivo = txtFile.lerArquivoTxt();

        linhasArquivo.forEach(System.out::println);

    }

    public static void main(String[] args) throws FileNotFoundException {

        adcionarVeiculosAFrota();
        criarRotasEAdcionar();
        adcionarVeiculosNoTxt();
        lerEPrintarArquivoTxt();
        //mostrarFrotaVeiculos();

    }

}
