//package cryss.dev.btgpactual.ms_order.producer;
//
//import cryss.dev.btgpactual.ms_order.consumer.Receiver;
//import lombok.RequiredArgsConstructor;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.TimeUnit;
//
//@Component
//@RequiredArgsConstructor
//public class Runner implements CommandLineRunner {
//
//    private final RabbitTemplate rabbitTemplate;
//    private final Receiver receiver;
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Sending message...");
//        rabbitTemplate.convertAndSend ("btg-pactual-order-created", "{\n" +
//                "  \"codigoPedido\": 1001,\n" +
//                "  \"codigoCliente\": 1,\n" +
//                "  \"itens\": [\n" +
//                "    {\n" +
//                "      \"produto\": \"lápis\",\n" +
//                "      \"quantidade\": 100,\n" +
//                "      \"preco\": 1.1\n" +
//                "    },\n" +
//                "    {\n" +
//                "      \"produto\": \"caderno\",\n" +
//                "      \"quantidade\": 50,\n" +
//                "      \"preco\": 5\n" +
//                "    }\n" +
//                "  ]\n" +
//                "}\n");
//        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
//    }
//}
