import BookmarkListBody from "@/components/Bookmark/BookmarkListBody";
import BookmarkListHeader from "@/components/Bookmark/BookmarkListHeader";

const Home = () => {
  return (
    <div className="container-fluid">
      <div className="container column mt-5">
        <BookmarkListHeader />
        <BookmarkListBody />
      </div>
    </div>
  );
};

export default Home;
