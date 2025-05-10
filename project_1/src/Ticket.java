public class Ticket{
    private String ticketId;
    private String seatNumber;
    private String showTime;
    private double price;

    public Ticket(String ticketId, String seatNumber, String showTime, double price) {
        this.ticketId = ticketId;
        this.seatNumber = seatNumber;
        this.showTime = showTime;
        this.price = price;
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public String getShowTime() {
        return showTime;
    }

    public double getPrice() {
        return price;
    }
}