public class StubWebComponent implements WebComponent{
    @Override
    public String visualizeBook(String ebookName) {
        if(ebookName == null)
            return "Error: Null Ebook Provided";
        if(ebookName.equals(""))
            return "Error: Empty Ebook Provided";
        if(ebookName.contains(".pdf") || ebookName.contains(".epub"))
            return "Success: Visualizing Ebook";
        else
            return "Error: Invalid Ebook Format";
    }
}
