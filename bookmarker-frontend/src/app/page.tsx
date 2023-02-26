import BookmarkListBody from "@/components/Bookmark/BookmarkListBody";
import BookmarkListFooter from "@/components/Bookmark/BookmarkListFooter";
import BookmarkListHeader from "@/components/Bookmark/BookmarkListHeader";
import ColorPalette from "@/components/Theme";

const Home = () => {
  return (
    <div className="container-fluid">
      {/* <h1 style={{ color: ColorPalette.DarkTheme.primaryColor }}>Hello</h1> */}
      <div className="container column mt-5">
        <BookmarkListHeader />
        <BookmarkListBody />
        <BookmarkListFooter />
      </div>
    </div>
  );
};

export default Home;
