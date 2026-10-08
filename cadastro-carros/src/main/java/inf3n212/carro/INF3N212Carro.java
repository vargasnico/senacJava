/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inf3n212.carro;

import controller.CCarro;
import controller.CPessoa;
import java.util.InputMismatchException;
import java.util.Scanner;
import model.Carro;
import model.Pessoa;
import servicos.CarroServicos;
import servicos.PessoaServicos;
import servicos.ServicosFactory;
import util.Validadores;
import view.JFPrincipal;

/**
 *
 * @author jbferraz
 */
public class INF3N212Carro {

    public static CPessoa cadPessoa = new CPessoa();
    public static CCarro cadCarro = new CCarro();
    static Scanner leia = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFPrincipal janela = new JFPrincipal();
        janela.setVisible(true);
        /*
        cadPessoa.mockPessoas();
        cadCarro.mockCarros();
        int opM = 99;
        int opSM = 99;
        do {
            System.out.println("-- Sistema de Cadastro --");
            menuP();
            opM = leiaNumInt();
            switch (opM) {
                case 1:
                case 2:
                    do {
                        subMenu(opM);
                        opSM = leiaNumInt();
                        switch (opSM) {
                            case 1:
                                if (opM == 1) {
                                    cadastrarPessoa();
                                } else {
                                    cadastrarCarro();
                                }
                                break;
                            case 2:
                                System.out.println("-- Editar --");
                                if (opM == 1) {
                                    editarPessoa();
                                } else {
                                    editarCarro();
                                }
                                break;
                            case 3:
                                if (opM == 1) {
                                    listarPessoa();
                                } else {
                                    listarCarro();
                                }
                                break;
                            case 4:
                                System.out.println("-- Deletar --");
                                if (opM == 1) {
                                    deletarPessoa();
                                } else {
                                    deletarCarro();
                                }
                                break;
                            case 0:

                                break;
                            default:
                                System.out.println("Opção inválida, tente novamente!");
                        }
                    } while (opSM != 0);
                    break;
                case 0:
                    System.out.println("Aplicação encerrada pelo usuário!");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");
            }
        } while (opM != 0);
*/
    }//fim método main

    public static int leiaNumInt() {
        Scanner leiaNum = new Scanner(System.in);
        try {
            return leiaNum.nextInt();
        } catch (InputMismatchException i) {
            System.out.println("Erro: " + i.getMessage() + "\nTente novamente!");
            leiaNumInt();
        }
        return 99;
    }

    public static void menuP() {
        System.out.println("-- Menu Principal --");
        System.out.println("1 - Ger. Pessoa");
        System.out.println("2 - Ger. Carro");
        System.out.println("0 - Sair");
        System.out.print("Digite aqui: ");
    }//fim menuP

    public static void subMenu(int opM) {
        String subM = null;
        if (opM == 1) {
            subM = "Pessoa";
        }
        if (opM == 2) {
            subM = "Carro";
        }
        System.out.println("-- Ger. " + subM + " --");
        System.out.println("1 - Cadastrar " + subM);
        System.out.println("2 - Editar " + subM);
        System.out.println("3 - Listar " + subM + "s");
        System.out.println("4 - Deletar " + subM);
        System.out.println("0 - Voltar");
        System.out.print("Digite aqui: ");
    }// fim subMenu

    private static void cadastrarPessoa() {
        System.out.println("-- Cadastro de Pessoa --");
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        int idPessoa;
        String nome;
        String cpf;
        String endereco;
        String telefone;
        boolean tcpf = true;

        do {
            System.out.print("Informe o CPF: ");
            cpf = leia.nextLine();
            tcpf = Validadores.isCPF(cpf);
            if (tcpf) {
                if (pessoaS.getPessoaByDoc(cpf).getCpf() != null) {
                    System.out.println("CPF já cadastrado!");
                    System.out.println("1 - Tentar novamente");
                    System.out.println("2 - Cancelar cadastro");
                    System.out.print("Digite aqui: ");
                    int op = leiaNumInt();
                    if (op == 2) {
                        return;
                    }
                } else {
                    tcpf = false;
                }
            } else {
                System.out.println("CPF inválido!");
                System.out.println("1 - Tentar novamente");
                System.out.println("2 - Cancelar cadastro");
                System.out.print("Digite aqui: ");
                int op = leiaNumInt();
                if (op == 2) {
                    return;
                }
                tcpf = true;
            }
        } while (tcpf);
        System.out.print("Informe o nome: ");
        nome = leia.nextLine();
        System.out.print("Informe o endereço: ");
        endereco = leia.nextLine();
        System.out.print("Informe o telefone: ");
        telefone = leia.nextLine();
        idPessoa = cadPessoa.geraID();
        Pessoa p = new Pessoa(idPessoa, nome, cpf, endereco, telefone);
        //cadPessoa.addPessoa(p);
        pessoaS.cadastroPessoa(p);
        System.out.println(p.getNome() + " cadastrado com sucesso!");
    }

    private static void cadastrarCarro() {
        System.out.println("-- Cadastrar Carro --");
        String placa;
        String marca;
        String modelo;
        int anoFab;
        int anoMod;
        String cor;
        String tpCambio;
        String combustivel;
        Pessoa proprietario;
        boolean pCarro = true;
        CarroServicos carroS = ServicosFactory.getCarroServicos();
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        do {
            System.out.print("Informe a Placa: ");
            placa = leia.nextLine();
            placa = placa.toUpperCase();
            pCarro = Validadores.validarPlaca(placa);
            if (pCarro) {
                //Carro carro = cadCarro.getCarroPlaca(placa);
                Carro carro = carroS.getCarroByDoc(placa);
                if (carro.getPlaca() == null) {
                    System.out.print("Informe a marca: ");
                    marca = leia.nextLine().toUpperCase();
                    System.out.print("Informe o modelo: ");
                    modelo = leia.nextLine().toUpperCase();
                    do {
                        System.out.print("Informe o ano fab.: ");
                        anoFab = leiaNumInt();
                        System.out.print("Informe o ano modelo: ");
                        anoMod = leiaNumInt();
                        if (!Validadores.validarAnoCarro(anoFab, anoMod)) {
                            System.out.println("Ano inválido, tente novamente!");
                        }
                    } while (!Validadores.validarAnoCarro(anoFab, anoMod));
                    System.out.print("Informe a cor: ");
                    cor = leia.nextLine().toUpperCase();
                    System.out.print("Informe tipo de câmbio: ");
                    tpCambio = leia.nextLine().toUpperCase();
                    System.out.print("Informe o combustível: ");
                    combustivel = leia.nextLine().toUpperCase();
                    do {
                        System.out.print("Informe o CPF do proprietário: ");
                        String cpf = leia.nextLine();
                        //proprietario = cadPessoa.getPessoaCPF(cpf);
                        proprietario = pessoaS.getPessoaByDoc(cpf);
                        if (proprietario.getCpf() == null) {
                            System.out.println("CPF não cadastrado,"
                                    + " tente novamente!");
                        } else {
                            System.out.println("Pessoa selecionada: "
                                    + proprietario.getNome());
                            System.out.println("Este é o proprietário?");
                            System.out.println("1 - Sim | 2- Não");
                            System.out.print("Digite aqui: ");
                            int op = leiaNumInt();
                            if (op == 2) {
                                System.out.println("Tente outro CPF.");
                                proprietario.setCpf(null);
                            }
                        }
                    } while (proprietario.getCpf() == null);
                    pCarro = false;
                    Carro c = new Carro(placa, marca, modelo, anoFab, anoMod, cor, tpCambio, combustivel, proprietario);
                    //cadCarro.addCarro(c);
                    carroS.cadastroCarro(c);
                    System.out.println("Carro cadastrado com sucesso!");
                } else {
                    System.out.println("Placa já cadastrada.");
                    pCarro = false;
                }
            } else {
                System.out.println("Placa inválida! Tente novamente!");
                pCarro = true;
            }
        } while (pCarro);
    }

    private static void editarPessoa() {
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        System.out.println("-- Editar Pessoa --");
        boolean isCPF;
        do {
            System.out.print("Informe o CPF da Pessoa a ser editado: ");
            String cpf = leia.nextLine();
            isCPF = Validadores.isCPF(cpf);
            if (isCPF) {
                Pessoa p = pessoaS.getPessoaByDoc(cpf);
                if (p.getCpf() != null) {
                    do {
                        System.out.println("Quais dados de " + p.getNome() + " deseja alterar?");
                        System.out.println("1 - Nome");
                        System.out.println("2 - Endereço");
                        System.out.println("3 - Telefone");
                        System.out.println("4 - Todos");
                        System.out.println("0 - Voltar");
                        System.out.print("Digite aqui: ");
                        int op = leiaNumInt();
                        if (op == 1 || op == 4) {
                            System.out.print("Informe o novo nome: ");
                            p.setNome(leia.nextLine());
                        }
                        if (op == 2 || op == 4) {
                            System.out.print("Informe o novo endereço: ");
                            p.setEndereco(leia.nextLine());
                        }
                        if (op == 3 || op == 4) {
                            System.out.print("Informe o novo telefone: ");
                            p.setTelefone(leia.nextLine());
                        }
                        if (op == 0) {
                            System.out.println("Operação cancelada pelo usuário!");
                            isCPF = false;
                        }
                        if (op < 0 || op > 4) {
                            System.out.println("Opção inválida, tente novamente!");
                        }
                        if (op > 0 && op < 4) {
                            pessoaS.atualizarPessoa(p);
                        }
                    } while (isCPF);
                } else {
                    System.out.println("CPF não cadastrado!");
                    isCPF = false;
                }
            } else {
                System.out.println("CPF inválido!");
                System.out.print("Deseja tentar novamente? \n 1 - Sim | 2 - Não: ");
                int op = leiaNumInt();
                if (op == 1) {
                    isCPF = true;
                } else {
                    isCPF = false;
                }
            }
        } while (isCPF);
    }

    private static void editarCarro() {
        System.out.println("-- Editar Carro --");
        CarroServicos carroS = ServicosFactory.getCarroServicos();
        boolean isPlaca;
        do {
            System.out.print("Informe a placa: ");
            String placa = leia.nextLine();
            placa = placa.toUpperCase();
            isPlaca = Validadores.validarPlaca(placa);
            if (isPlaca) {
                Carro c;
                c = carroS.getCarroByDoc(placa);
                if (c.getPlaca() != null) {
                    System.out.println(c.toString());
                    System.out.println("O que deseja alterar?");
                    System.out.println("1 - Cor");
                    System.out.println("2 - Tipo de câmbio");
                    System.out.println("3 - Tipo de combustível");
                    System.out.println("4 - Proprietário");
                    System.out.println("5 - Todos");
                    System.out.println("0 - Cancelar");
                    System.out.print("Digite sua escolha aqui: ");
                    int op = leiaNumInt();
                    if (op == 1 || op == 5) {
                        System.out.print("Informe a nova cor: ");
                        c.setCor(leia.nextLine().toUpperCase());
                    }
                    if (op == 2 || op == 5) {
                        System.out.print("Informe o novo câmbio: ");
                        c.setTpCambio(leia.nextLine().toUpperCase());
                    }
                    if (op == 3 || op == 5) {
                        System.out.print("Informe o novo combustível: ");
                        c.setCombustivel(leia.nextLine().toUpperCase());
                    }
                    if (op == 4 || op == 5) {
                        boolean isCPF;
                        do {
                            System.out.print("Informe o CPF proprietário: ");
                            String cpf = leia.nextLine();
                            isCPF = Validadores.isCPF(cpf);
                            if (isCPF) {
                                Pessoa p = cadPessoa.getPessoaCPF(cpf);
                                if (p != null) {
                                    System.out.println("Pessoa selecionada: " + p.getNome());
                                    System.out.println("Está correto?");
                                    System.out.println("1 - Sim | 2 - Não");
                                    System.out.print("Digite aqui: ");
                                    op = leiaNumInt();
                                    if (op == 1) {
                                        isCPF = false;
                                        c.setProprietario(p);
                                    }
                                } else {
                                    System.out.println("CPF não encontrado!");
                                    System.out.println("1 - Cadastrar?");
                                    System.out.println("2 - Tentar novamente?");
                                    System.out.print("Digite aqui sua opção: ");
                                    int op2 = leiaNumInt();
                                    if (op2 == 1) {
                                        cadastrarPessoa();
                                    }
                                }
                            } else {
                                System.out.println("CPF inválido, tente novamente!");
                                isCPF = true;
                            }
                        } while (isCPF);
                    }
                    if (op == 0) {
                        System.out.println("Edição do carro cancelada pelo usuário!");
                        isPlaca = false;
                    }
                    if (op < 0 || op > 5) {
                        System.out.println("Opção inválida!");
                    }else{
                        carroS.atualizarCarro(c);
                    }
                    isPlaca = false;
                } else {
                    System.out.println("Placa não cadastrada!");
                    isPlaca = true;
                }
            } else {
                System.out.println("Placa informada inválida!");
                System.out.println("Deseja tentar novamente?");
                System.out.println("1 - Sim | 2 - Cancelar");
                System.out.print("Digite aqui: ");
                int op = leiaNumInt();
                if (op == 1) {
                    isPlaca = true;
                }
            }
        } while (isPlaca);
    }

    private static void listarPessoa() {
        System.out.println("-- Lista de Pessoas --");
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        if (pessoaS.getPessoas().isEmpty()) {
            System.out.println("Não tem Pessoas cadastras no sistema!");
        } else {
            for (Pessoa pessoa : pessoaS.getPessoas()) {
                System.out.println(pessoa.toString());
            }
        }
    }

    private static void listarCarro() {
        System.out.println("-- Lista de Carros --");
        CarroServicos carroS = ServicosFactory.getCarroServicos();
        if (carroS.getCarros().isEmpty()) {
            System.out.println("Não tem Pessoas cadastras no sistema!");
        } else {
            for (Carro carro : carroS.getCarros()) {
                System.out.println(carro.toString());
            }
        }
    }

    private static void deletarPessoa() {
        PessoaServicos pessoaS = ServicosFactory.getPessoaServicos();
        System.out.println("-- Deletar Pessoa --");
        boolean delCPF = false;
        do {
            System.out.print("Informe o CPF da Pessoa a ser deletada: ");
            String cpf = leia.nextLine();
            delCPF = Validadores.isCPF(cpf);
            if (delCPF) {
                Pessoa p = pessoaS.getPessoaByDoc(cpf);
                if (p.getCpf() != null) {
                    System.out.println("Deseja realmente deletar " + p.getNome() + "?");
                    System.out.print("1 - Sim | 2 - Não: ");
                    int op = leiaNumInt();
                    if (op == 1) {
                        //cadPessoa.removePessoa(p);
                        pessoaS.deletarPessoa(cpf);
                        System.out.println("Pessoa deletada com sucesso!");
                        delCPF = false;
                    } else {
                        System.out.println("Operação cancelada pelo usuário!");
                        delCPF = false;
                    }
                } else {
                    System.out.println("CPF não cadastrado!");
                    System.out.println("Deseja tentar novamente?");
                    System.out.print("1 - Sim | 2 - Não: ");
                    int op = leiaNumInt();
                    if (op == 1) {
                        delCPF = true;
                    } else {
                        delCPF = false;
                    }
                }
            } else {
                System.out.println("CPF inválido!"
                        + "\nTente novamente.");
                delCPF = true;
            }
        } while (delCPF);
    }//fim do deletarPessoa

    private static void deletarCarro() {
        System.out.println("-- Deletar Carro --");
        Carro c;
        do {
            System.out.print("Informe uma placa: ");
            String placa = leia.nextLine().toUpperCase();
            CarroServicos carroS = ServicosFactory.getCarroServicos();
            c = carroS.getCarroByDoc(placa);
            if (c.getPlaca() != null) {
                System.out.println("Deseja realmente deletar o Carro "
                        + c.getPlaca() + " | " + c.getModelo());
                System.out.println("1 - Sim | 2 - Cancelar");
                System.out.print("Digite aqui: ");
                int op = leiaNumInt();
                if (op == 1) {
                    carroS.deletarCarro(c.getPlaca());
                    System.out.println("Carro deletado com sucesso.");
                } else {
                    System.out.println("Operação cancelada pelo usuário.");
                }
            } else {
                System.out.println("Placa inválida!");
            }
        } while (c.getPlaca() == null);
    }

}//fim classe
