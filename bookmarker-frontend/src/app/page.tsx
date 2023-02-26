import BookmarkApiConsumer from "@/components/Bookmark/api/BookmarkApiConsumer";
import BookmarkListBody from "@/components/Bookmark/BookmarkListBody";
import BookmarkListFooter from "@/components/Bookmark/BookmarkListFooter";
import BookmarkListHeader from "@/components/Bookmark/BookmarkListHeader";

const Home = async () => {
  var data = await BookmarkApiConsumer.fetchBookmarks();
  return (
    <div className="container-fluid">
      <div className="container column mt-5">
        <BookmarkListHeader />
        <BookmarkListBody items={data.bookmarks} />
        <BookmarkListFooter
          totalPages={data.totalPages}
          totalElements={data.totalElements}
          currentPage={data.currentPage}
        />
      </div>
    </div>
  );
};

export default Home;
