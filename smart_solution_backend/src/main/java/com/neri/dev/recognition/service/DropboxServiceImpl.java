package com.neri.dev.recognition.service;


import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderResult;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderBuilder;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import java.io.InputStream;

import com.neri.dev.recognition.exception.*;
import org.springframework.stereotype.Service;

@Service
public class DropboxServiceImpl implements DropboxService {

    private final DbxClientV2 client;

    public DropboxServiceImpl(DbxClientV2 client) {
        this.client = client;
    }

    @Override
    public InputStream downloadFile(String filePath) {
        return handleDropboxAction(() -> client.files().download(filePath).getInputStream(),
                String.format("Erro ao fazer download do arquivo: %s", filePath));
    }

    @Override
    public FileMetadata uploadFile(String filePath, InputStream fileStream) {
        return handleDropboxAction(() -> client.files().uploadBuilder(filePath).uploadAndFinish(fileStream),
                String.format("Erro ao fazer Upload do Arquivo: %s", filePath));
    }

    @Override
    public CreateFolderResult createFolder(String folderPath) {
        return handleDropboxAction(() -> client.files().createFolderV2(folderPath), "Erro ao criar Pasta");
    }

    @Override
    public FolderMetadata getFolderDetails(String folderPath) {
        return getMetadata(folderPath, FolderMetadata.class, String.format("Erro ao pegar os detalhes da pasta: %s", folderPath));
    }

    @Override
    public FileMetadata getFileDetails(String filePath) {
        return getMetadata(filePath, FileMetadata.class, String.format("Erro ao pegar os detalhes do arquivo: %s", filePath));
    }

    @Override
    public ListFolderResult listFolder(String folderPath, boolean recursiveListing, long limit) {
        ListFolderBuilder listFolderBuilder = client.files().listFolderBuilder(folderPath);
        listFolderBuilder.withRecursive(recursiveListing);
        listFolderBuilder.withLimit(limit);

        return handleDropboxAction(listFolderBuilder::start, String.format("Erro ao lista pasta: %s", folderPath));
    }

    @Override
    public ListFolderResult listFolderContinue(String cursor) {
        return handleDropboxAction(() -> client.files().listFolderContinue(cursor), "Erroao listar pasta");
    }

    @Override
    public void deleteFile(String filePath) {
        handleDropboxAction(() -> client.files().deleteV2(filePath), String.format("Erro ao deletar arquivo: %s", filePath));
    }

    @Override
    public void deleteFolder(String folderPath) {
        handleDropboxAction(() -> client.files().deleteV2(folderPath), String.format("Erro ao deletar pasta: %s", folderPath));
    }

    private <T> T handleDropboxAction(DropboxActionResolver<T> action, String exceptionMessage) {
        try {
            return action.perform();
        } catch (Exception e) {
            String messageWithCause = String.format("%s causa: %s", exceptionMessage, e.getMessage());
            throw new DropboxException(messageWithCause, e);
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T getMetadata(String path, Class<T> type, String message) {
        Metadata metadata = handleDropboxAction(() -> client.files().getMetadata(path),
                String.format("Erro ao acessar detalhes(Metadata): %s", path));

        checkIfMetadataIsInstanceOfGivenType(metadata, type, message);
        return (T) metadata;
    }

    private <T> void checkIfMetadataIsInstanceOfGivenType(Metadata metadata, Class<T> validType, String exceptionMessage) {
        boolean isValidType = validType.isInstance(metadata);
        if (!isValidType) {
            throw new DropboxException(exceptionMessage);
        }
    }
}
