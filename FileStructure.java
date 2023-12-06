import java.util.ArrayList;
import java.util.Iterator;

public class FileStructure {
    private NLNode<FileObject> root;

    // Constructor that creates the root node for the file structure
    // and adds child nodes if the root is a directory
    public FileStructure(String fileObjectName) throws FileObjectException {
        FileObject newFile = new FileObject(fileObjectName); // create object for root
        NLNode<FileObject> r = new NLNode<>(newFile, null);
        root = r; //set r as root
        if (r.getData().isDirectory()) { //if root contains more sub-folders then
            addChildNodes(r); //call addchild nodes
        }
    }

    // Recursively adds child nodes to the given node if it is a directory
    private void addChildNodes(NLNode<FileObject> node) throws FileObjectException {
        if (node.getData().isFile()) { //if node is file do nothing
            return;
        } else {
            FileObject f = node.getData();
            Iterator<FileObject> z = f.directoryFiles();
            while (z.hasNext()) { //iterate through iterator
                FileObject childFile = z.next();
                NLNode<FileObject> childNode = new NLNode<>(childFile, node); //create fileobject node of childfile set parent as the node received in function
                node.addChild(childNode); //make it a child
                addChildNodes(childNode); //recursively call the function
            }
        }
    }

    // Returns the root node of the file structure
    public NLNode<FileObject> getRoot() {
        return root;
    }

    // Returns an iterator of the names of files in the file structure
    // that have the given file extension
    public Iterator<String> filesOfType(String type) {
        ArrayList<String> myArray = new ArrayList<>();//create empty arraylist
        fileType(root, type, myArray); //pass in filetype with parameters the root, the type, and the array
        return myArray.iterator(); //return the filled array into iterator
    }

    // Recursively adds the names of files in the file structure that have
    // the given file extension to the given array list
    private void fileType(NLNode<FileObject> node, String type, ArrayList<String> myArray) {
        if (node.getData().isFile()) { //if node is a file
            if (node.getData().getLongName().endsWith(type)) { //and if it ends with the specific type
                myArray.add(node.getData().getLongName()); //add the long name to array
            }
        } else { //otherwise do this
            Iterator<NLNode<FileObject>> myChildren = node.getChildren();
            while (myChildren.hasNext()) {
                fileType(myChildren.next(), type, myArray); //iterate through the iterator of children and recursively call the function until while loop is finished
            }
        }
    }

    // Returns the long name of the first file in the file structure
    // that has the given name, or an empty string if no such file exists
    public String findFile(String name) {
        ArrayList<String> myArray = new ArrayList<>(); //create empty arraylist
        findFilez(root, name, myArray);
        if (myArray.size() == 0) { //if array is empty then there is no file with such name
            return "";
        }
        return myArray.get(0); //if there is then the first element is the file name found
    }

    // Recursively adds the long names of files in the file structure that
    // have the given name to the given array list
    private void findFilez(NLNode<FileObject> node, String name, ArrayList<String> myArray) {
        if (node.getData().isFile()) { //if the node is a file
            if (node.getData().getName().equals(name)) { //and the name is equal to the target name
                myArray.add(node.getData().getLongName()); //add to array
            }
        } else { //if not
            Iterator<NLNode<FileObject>> myChildren = node.getChildren(); //get children
            while (myChildren.hasNext()) { //iterate through children recursively calling the function until the iteration is finished
                findFilez(myChildren.next(), name, myArray);
            }
        }
    }
}