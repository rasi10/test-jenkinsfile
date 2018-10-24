/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.accommodation.restfull.utils;

/**
 *
 * @author JoaoPaulo
 */
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class Hash {
 
    /**
     * Hash MD5 de um Conteúdo em String
     * @param input
     * @throws NoSuchAlgorithmException
     */
    public static String md5(final String input)
        throws NoSuchAlgorithmException {
        return md5(input.getBytes(Charset.forName("UTF-8")));
    }
     
    /**
     * Hash MD5 de um Conteúdo em Array de Bytes
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5(final byte[] input)
        throws NoSuchAlgorithmException {
        return doHash("MD5", input);
    }
     
    /**
     * Hash SHA-1 de um Conteúdo em String
     * @param input
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(final String input)
        throws NoSuchAlgorithmException {
        return sha1(input.getBytes(Charset.forName("UTF-8")));
    }
     
     
    /**
     * Hash SHA-1 de um Conteúdo em Array de Bytes
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(final byte[] input)
        throws NoSuchAlgorithmException {
        return doHash("SHA1", input);
    }
     
     
    /**
     * Hash SHA-256 de um Conteúdo em String
     * @param input
     * @throws NoSuchAlgorithmException
     */
    public static String sha256(final String input)
        throws NoSuchAlgorithmException {
        String sha = sha256(input.getBytes(Charset.forName("UTF-8")));
        return sha;
    }
     
    /**
     * Hash SHA-256 de um Conteúdo em Array de Bytes
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha256(final byte[] input)
        throws NoSuchAlgorithmException {
        return doHash("SHA-256", input);
    }
     
    /**
     * Hash SHA-512 de um Conteúdo em String
     * @param input
     * @throws NoSuchAlgorithmException
     */
    public static String sha512(final String input)
        throws NoSuchAlgorithmException {
        return sha512(input.getBytes(Charset.forName("UTF-8")));
    }
     
    /**
     * Hash SHA-512 de um Conteúdo em Array de Bytes
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha512(final byte[] input)
        throws NoSuchAlgorithmException {
        return doHash("SHA-512", input);
    }
     
    /**
     * Hash Genérico de um Conteúdo em Array de Bytes
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String doHash(final String algorithm, final byte[] input)
        throws NoSuchAlgorithmException {
     
        // Cria Objetos
        MessageDigest mDigest;
        byte[] result;
         
        // Inicializa o Digest
        mDigest = MessageDigest.getInstance(algorithm);
         
        // Converte para Array de Bytes
        result = mDigest.digest(input);
         
        // Retorna (Convertendo para Hexa)
        StringBuilder sb = new StringBuilder();
         for(int i=0; i< result.length ;i++){
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
         }
         return sb.toString();
//        char[] test = org.apache.commons.codec.binary.Hex.encodeHex(result, true);
//        return new String(test);
    }
}
