
interface Bookmark {
  title: string,
  url: string,
  createdAt: string,
  updatedAt?: string
}
interface BookmarkData {
  id: number,
  totalPages: number,
  totalElements: number,
  currentPage: number,
  bookmarks: [Bookmark]
}

class BookmarkApiConsumer {
  static API = "http://localhost:18080/api/bookmarks";
  static callAPI = async (apiUrl: string) => {
    try {
      console.log("calling api")
      return await fetch(apiUrl);
    } catch (err) {
      console.log("[callAPI] " + err);
    }
  };
  static async fetchBookmarks(page?: number) {
    let modifiedApiUrl = this.API;
    if (page != undefined) {
      modifiedApiUrl += "?page=" + page;
    }
  return await this.callAPI(modifiedApiUrl)
    .then((response) => response?.json())
    .then((data) => data as BookmarkData)
  }
}
export default BookmarkApiConsumer;
