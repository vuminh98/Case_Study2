public class Course {
    private static int INDEX = 0;
    private Long id;
    private Name name;
    private Double price;
    private Integer quantity;
    private Category category;

    public Course(Name name, Double price, Integer quantity, Category category) {
        this.id = Long.valueOf(++INDEX);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if (category == null) {
            return "Course{" +
                    "id=" + id +
                    ", name= " + name.getName() + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", category= null" +
                    '}';
        } else if (name == null) {
            return "Course{" +
                    "id=" + id +
                    ", name= null " + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", category= " + category.getName() +
                    '}';
        } else {
            return "Course{" +
                    "id=" + id +
                    ", name='null" + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    ", category= null" +
                    '}';
        }

//        public void display() {
//            if (category != null) {
//                System.out.printf("%-10s%-10s%-15s%-20s%s", id, name, price, quantity, category.getName() + "\n");
//            } else if (name != null) {
//                System.out.printf("%-10s%-10s%-15s%-20s%s", id, name.getName(), price, quantity, category.getName() + "\n");
//            } else {
//                System.out.printf("%-10s%-10s%-15s%-20s%s", id, "null", price, quantity, "null\n");
//            }
//        }
//
    }

    public void display() {
        if (category != null && name != null) {
                System.out.printf("%-10s%-35s%-15s%-20s%s", id, name.getName(), price, quantity, category.getName() + "\n");
            } else {
                System.out.printf("%-10s%-35s%-15s%-20s%s", id, "null", price, quantity, "null\n");
            }
    }
}
