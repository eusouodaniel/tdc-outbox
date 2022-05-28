const amqplib = require("amqplib");
const ampqLocation = `amqp://${process.env.amqp_username}:${process.env.amqp_password}@${process.env.amqp_hostname}:${process.env.amqp_port}`;

let connectionEstablished = false;

start();

async function start() {
  do {
    try {
      await startListening();
      connectionEstablished = true;
    } catch (err) {
      console.error("Connection is failed");
    }
    await sleep(2000);
  } while (!connectionEstablished);
}

async function startListening() {
  return new Promise((resolve, reject) => {
    amqplib.connect(ampqLocation).then((connection) => {
        resolve();
        return connection.createChannel();
      }).catch(reject).then((channel) => {
        channel.assertQueue(process.env.user_queue).then(() => {
          channel.consume(process.env.user_queue, (message) => {
            const messageContent = JSON.parse(message.content.toString());
            console.log(`Received message with id: '${messageContent.id}'`);
            console.log(`Message: ${JSON.stringify(messageContent)}`);
            channel.ack(message);
          });
        }).catch(reject);
      }).catch(reject);
  });
}

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}