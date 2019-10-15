package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class CopiaDiretorio {
	private static FileInputStream source;
	private static FileOutputStream destination;

	public static void copiar(File origem, File destino) throws IOException {

        source = new FileInputStream(origem);
        destination = new FileOutputStream(destino);

        FileChannel sourceFileChannel = source.getChannel();
        FileChannel destinationFileChannel = destination.getChannel();

        long size = sourceFileChannel.size();
        sourceFileChannel.transferTo(0, size, destinationFileChannel);

    }
	
	public static void copiarDiretorios(File origem, File destino) throws IOException, UnsupportedOperationException {
        if (!destino.exists()) {
            destino.mkdirs();
        }
        
        if (!origem.isDirectory()) {
            throw new UnsupportedOperationException("Origem deve ser um diretório");
        }
        if (!destino.isDirectory()) {
            throw new UnsupportedOperationException("Destino deve ser um diretório");
        }
        File[] files = origem.listFiles();
        
        
        for (int i = 0; i < files.length ; ++i) {
        	if (files[i].isDirectory()) {
                copiarDiretorios(files[i], new File(destino + "\\" + files[i].getName()));
            } else {
                System.out.println(i +" "+ files[i].getName());
                copiar(files[i], new File(destino + "\\" + files[i].getName()));
            }
        }
    }

	public static void main(String[] args) throws IOException {
		/*File origem = new File("C:\\SVN-PROJETOS\\SISTEMAS\\WEBMANAGER\\");
		File destino = new File("C:\\temp\\teste");
		copiarDiretorios(origem,destino);*/
	
	}
}
