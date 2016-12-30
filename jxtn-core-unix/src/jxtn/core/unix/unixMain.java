package jxtn.core.unix;

public class unixMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length <= 0) {
			throw new IllegalArgumentException("Must specified a network interface name");
		}

		boolean interrupted = false;

		String ifName = args[0];
		int sockfd = NetTrafficCapture.start(ifName);
		System.out.println("sockfd:" + String.valueOf(sockfd));

		while (!interrupted) {
			byte[] packet = NetTrafficCapture.receive(sockfd);
			System.out.print(new String(packet));
		}
	}

}
