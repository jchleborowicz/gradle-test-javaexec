package pl.jch;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

public class PrintTask extends DefaultTask {

    @TaskAction
    public void printSth() throws UnsupportedEncodingException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        getProject().javaexec(javaExecSpec -> {
            javaExecSpec.args("abc", "def", "gah");
            javaExecSpec.classpath(getProject().getConfigurations().getByName("printoutsConfig"));
            javaExecSpec.setMain("org.apache.kafka.clients.admin.AdminClientConfig");
            javaExecSpec.setStandardOutput(outputStream);
        });

        final String message = new String(outputStream.toByteArray(), "UTF-8");
        final String message2 = message.substring(0, Math.min(50, message.length()));

        System.out.println("****");
        System.out.println(message2);
        System.out.println("****");
        System.out.println(message2.toUpperCase());
        System.out.println("****");
    }

}
