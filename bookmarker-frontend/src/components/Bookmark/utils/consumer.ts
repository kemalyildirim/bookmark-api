import { BookmarkData } from "@/components/Bookmark/types/bookmark";

const BASE_API_URL = "http://localhost:18080/";

const callAPI = async (apiUrl: string) => {
    console.log("calling api: " + apiUrl)
    return await fetch(apiUrl)
};
export async function getBookmarks(page?: number) {
  let modifiedApiUrl = BASE_API_URL;
  if (page != undefined) {
    modifiedApiUrl += `/api/bookmarks/?page=${page}`;
  }
return await callAPI(modifiedApiUrl)
  .then((response) => response?.json())
  .then((data) => data as BookmarkData)
  .catch((err) => {
    console.error("[getBookmarks] error with api call: ", err)
    return {} as BookmarkData
  })
}