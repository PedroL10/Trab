package application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

import entities.AbstractVehicle;
import entities.Caminhao;
import entities.Carro;
import entities.Frota;
import entities.Furgao;
import entities.Rota;
import entities.Van;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		Frota frota = new Frota();
		
		DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");		
		
		AbstractVehicle v1 = new Carro("Carro", "HB20", "AAA1234", 50, 50000.00, 0.0);
		AbstractVehicle v2 = new Van("Van", "Scania", "ABC2222", 60, 80000.00, 0.0);
		AbstractVehicle v3 = new Carro("Carro", "Golf","FOG2022",  50, 90000.00, 0.0);
		AbstractVehicle v4 = new Carro("Carro", "Gol","GOL2022",  50, 90000.00, 0.0);
		AbstractVehicle v5 = new Carro("Carro", "UNO","UNO2022",  50, 90000.00, 0.0);
		AbstractVehicle v6 = new Caminhao("Caminhao" ,"F500","CAM2022",  250, 90000.00, 0.0);
		AbstractVehicle v7 = new Furgao("Furgao", "AMG","AMG2022",  80, 90000.00, 0.0);

		frota.adicionarVeiculo(v1);
		frota.adicionarVeiculo(v2);
		frota.adicionarVeiculo(v3);
		frota.adicionarVeiculo(v4);		
		frota.adicionarVeiculo(v5);
		frota.adicionarVeiculo(v6);
		frota.adicionarVeiculo(v7);
		
		Rota r1 = new Rota(400.0, LocalDate.parse("05/10/2022", fmt1));
		Rota r2 = new Rota(300.0, LocalDate.parse("21/09/2022", fmt1));
		Rota r3 = new Rota(200.0, LocalDate.parse("22/09/2022", fmt1));
		Rota r4 = new Rota(500.0, LocalDate.parse("23/09/2022", fmt1));
		Rota r5 = new Rota(700.0, LocalDate.parse("24/09/2022", fmt1));		

		//v1.adicionaRota(r1);
		//v1.adicionaRota(r5);
		//v1.adicionaRota(r2);	


		int opcao =1;
		//Menu de opcoes
		while (opcao!=0){

			System.out.println("\n1 - Adicionar veiculos");
			System.out.println("2 - Mostrar veiculos da frota");
			System.out.println("3 - Localizar veiculos na frota");
			System.out.println("4 - Adicionar rota a veiculo da frota");
			System.out.println("5 - Mostrar rotas dos veiculos da frota");
			System.out.println("6 - Ler Arquivo");
			System.out.println("7 - Quilometragem media das rotas dos veiculos da frota");
			System.out.println("8 - Os 3 veiculos que mais fizeram rotas");
			System.out.println("9 - Buscar rotas por data");
			System.out.println("0-Sair");
			System.out.print("Opcao: ");
			opcao = sc.nextInt();
			
			switch(opcao) {
			case 1 -> {
				System.out.println("Entre com os dados do veículo");
				sc.nextLine();
				System.out.print("Digite o tipo de veiculo: ");
				String tipoDeVeiculo = sc.nextLine();
				System.out.print("Digite o modelo: ");
				String modelo = sc.nextLine();
				System.out.print("Digite a placa: ");
				String placa = sc.nextLine();
				System.out.print("Digite a capacidade do tanque: ");
				Integer capacidadeTanque = sc.nextInt();
				System.out.print("Digite o valor de venda: ");
				Double valorVenda = sc.nextDouble();
				System.out.print("Quilometragem: ");
				Double quilometragem = sc.nextDouble();
				
				AbstractVehicle vehicle = null;
				
				//if comparando tipo de veiculo para instanciar e adicionar o tipo correto na frota
				if(tipoDeVeiculo.equalsIgnoreCase("Carro")) {
					vehicle = new Carro(tipoDeVeiculo, modelo, placa, capacidadeTanque, valorVenda, quilometragem);
				} else if (tipoDeVeiculo.equalsIgnoreCase("Van")) {
					vehicle = new Van(tipoDeVeiculo, modelo, placa, capacidadeTanque, valorVenda, quilometragem);
				} else if (tipoDeVeiculo.equalsIgnoreCase("Caminhao")) {
					vehicle = new Caminhao(tipoDeVeiculo, modelo, placa, capacidadeTanque, valorVenda, quilometragem);
				} else if (tipoDeVeiculo.equalsIgnoreCase("Furgao")) {
					vehicle = new Furgao(tipoDeVeiculo, modelo, placa, capacidadeTanque, valorVenda, quilometragem);
				} else {
					System.out.println("Informe um modelo de veiculo válido");
				}
				
				// mostrar o veiculo
				System.out.println(vehicle);
				// confirmar adição de veiculo na frota
				System.out.println("Deseja adicionar o veiculo na frota: (s/n)");
				char resposta = sc.next().charAt(0);
				if (resposta == 's') {
					frota.adicionarVeiculo(vehicle);
					System.out.println("Veiculo adicionado na frota com sucesso");
					System.out.println();
				} else {
					System.out.print("Veiculo nao adicionado");
				}
				
				//mensagem informando que o veiculo foi adicionado
				
			}
			
			case 2 -> {
				if(frota.getVehicles().size() != 0) {
				for(AbstractVehicle v : frota.getVehicles()) {
					System.out.println(v);
				}
				} else {
					System.out.println("Nenhum veiculo na frota");
				}
			}
			
			case 3 -> {
				sc.nextLine();
				System.out.print("Informe a placa do veiculo: ");
				String placa = sc.nextLine();					
				
				for(AbstractVehicle v : frota.getVehicles()) {
					if(placa.equalsIgnoreCase(v.getPlaca())) {
					System.out.println("Veiculo encontrado");	
					System.out.println(v);
					}
				}
			}
			
			case 4 -> {
				
				System.out.println("Informe a distancia da rota: ");
				Double distancia  = sc.nextDouble();
				sc.nextLine();
				System.out.print("Informe a data da rota no formato dd/mm/aaaa: ");
				LocalDate data = LocalDate.parse(sc.nextLine(),fmt1);
				
				Rota rota = new Rota(distancia, data);				
				System.out.println(rota);
				
				System.out.print("Digite a placa do veiculo que receberá essa rota: ");
				String placa = sc.nextLine();
				
				for(AbstractVehicle v : frota.getVehicles()) {
					if(placa.equalsIgnoreCase(v.getPlaca())) {
					v.adicionaRota(rota);	
					System.out.println("Rota adicionada para o veiculo:");
					System.out.println(v.toString());
					}
				}
				
				Rota r10 = new Rota(400.0, LocalDate.parse("05/10/2022", fmt1));
				
			}
			case 5 -> {}
			case 6 -> {
				
				// Metodo para ler e salvar arquivos txt
				sc.nextLine();
				System.out.println("Enter file path: "); // /home/pedro/PastaTrabalho/veiculos.csv
				String sourceFileStr = sc.nextLine();
				
				File sourceFile = new File(sourceFileStr);
				String sourceFolderStr = sourceFile.getParent();
				
				boolean success = new File(sourceFolderStr + "/Relatorios").mkdir();
				
				String targetFileStr = sourceFolderStr + "/Relatorios/relatorio.csv";
				
				try(BufferedReader br = new BufferedReader(new FileReader(sourceFileStr)) ) {
					
					String itemCsv = br.readLine();
					while(itemCsv != null) {
						String[] fields = itemCsv.split(",");
						String tipoDeVeiculo = fields[0];
						String modelo = fields[1];
						String placa = fields[2];
						Integer capacidadeTanque = Integer.parseInt(fields[3]);
						Double valorVenda = Double.parseDouble(fields[4]);
						Double quilometragem = Double.parseDouble(fields[5]);
						
						//frota.adicionarVeiculo(new (modelo, placa, capacidadeTanque, valorVenda,quilometragem));
						itemCsv = br.readLine();
					}
					
					try(BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
						
						for(AbstractVehicle v : frota.getVehicles()) {
							bw.write(v.getModelo() + ", " + v.getPlaca());
							bw.newLine();
						}
						
						System.out.println(targetFileStr + " Relatorio Criado");
						System.out.println("---------------");
						
					} catch(IOException e) {
						System.out.println("Error ao escrever arquivo: " + e.getMessage()); 
					}
					
					
				} catch(IOException e) {
					System.out.println("Erro ao gerar arquivo: " + e.getMessage());
				}				
			}	
			
			case 7 -> {
				System.out.println( String.format("%.2f",frota.quilometragemMediaDasRotas(frota.getVehicles())));	
			}
			case 8 -> {
				System.out.println("Os tres veiculos com mais rotas: ");
			}
			case 9 -> {}
			default -> {
				System.out.println("Digite uma opção válida");				
			}
			
			}			
		}

		System.out.println("------------");		
		
	}

}