package org.ethsql;

import org.ethworks.client.ClientConfiguration;
import org.ethworks.client.EthAccountClient;
import org.ethworks.generated.SQLStorage;

import java.math.BigInteger;

public class Main {

    void run() throws Exception {
        ClientConfiguration clientConfiguration = new ClientConfiguration("src/main/resources/genache.conf");
        EthAccountClient ethAccountClient = new EthAccountClient(clientConfiguration);

        System.out.println("Account Address: " + clientConfiguration.getDeploymentAccountCredentials().getAddress());
        System.out.println("Account Private Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPrivateKey());
        System.out.println("Account Public Key: " + clientConfiguration.getDeploymentAccountCredentials().getEcKeyPair().getPublicKey());
        System.out.println("Account Balance(wei): " + ethAccountClient.getBalance());

        System.out.println("Initializing contract...");
        SQLStorage sqlStorage = ethAccountClient.initializeContract(SQLStorage.class, null);
        SQLParser sqlParser = new SQLParser(sqlStorage);
        System.out.println();

        /*
        Query format:
            CREATE:
                CREATE TABLE <table name> (<c1>, <c2>, ..., <cn>)

            INSERT:
                INSERT INTO <table name> (<c1>, <c2>, ..., <cn>) VALUES (<v11>, <v12>, ..., <vin>)(<v21>, <v22>, ..., <vjn>)...()

            SELECT:
                SELECT <num of columns> <start row> <end row> (<columns1, columns2, ..., columnsn>) FROM <table name>

            UPDATE:
                UPDATE <table name> SET (<c1> = <v1>, <c2> = <v2>, ..., <cn> = <vn>) WHERE <row index>
        */

        String query = "CREATE TABLE table1 (column1, column2, column3)";
        System.out.println("Creating table...");

        query = "INSERT INTO table1 (column1, column2, column3) VALUES (value11, value12, value13)(value21, value22, value23)(value31, value32, value33)";
        System.out.println("Inserting data...");

        sqlParser.parseQuery(query);
        System.out.println();

        query = "SELECT column1, column2 FROM table1";
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT column2 FROM table1";
        sqlParser.parseQuery(query);
        query = "SELECT column3 FROM table1";
        sqlParser.parseQuery(query);
        System.out.println();

        query = "UPDATE table1 SET column1 = value1, column2 = value2, column3 = value3 WHERE 2";
        System.out.println("Updating data...");
        sqlParser.parseQuery(query);
        System.out.println();


        query = "SELECT 1 0 2 (column1) FROM table1";
        System.out.println("Retrieving data...");
        sqlParser.parseQuery(query);
        query = "SELECT 1 0 2 (column2) FROM table1";
        sqlParser.parseQuery(query);
        query = "SELECT 1 0 2 (column3) FROM table1";
        sqlParser.parseQuery(query);
        System.out.println();

        /*
        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla,pepe,").send();

        System.out.println("Get Inserted Data...");
        System.out.println(sqlStorage.getValue("table1", "c1", BigInteger.ZERO).send());
        System.out.println(sqlStorage.getValue("table1", "c2", BigInteger.ZERO).send());

        System.out.println("Select");
        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());

        sqlStorage.insert("table1", new BigInteger("2"), "c1,c2,", "bla2,pepe2,").send();

        System.out.println("Select");
        System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ONE, BigInteger.ZERO, "c1,c2,").send());
<<<<<<< HEAD
*/
        //sqlStorage.update("table1", new BigInteger("2"),  BigInteger.ZERO, "c1,c2,", "bla3,pepe3,").send();

        //System.out.println("Select");
        //System.out.println(sqlStorage.getSelect("table1", new BigInteger("2"), BigInteger.ZERO, BigInteger.ZERO, "c1,c2,").send());
    }

    public static void main(String[] args) throws Exception {
        new Main().run();
    }
}
