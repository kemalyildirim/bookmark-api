"use client";

import React, { useCallback, useEffect, useState } from "react";
import { getBookmarks } from "@/components/Bookmark/utils/consumer";
import BookmarkListFooter from "@/components/Bookmark/BookmarkListFooter";
import BookmarkListItem from "@/components/Bookmark/BookmarkListItem";
import { BookmarkData } from "@/components/Bookmark/types/bookmark";

const BookmarkListBody = () => {
  const [currentBookmarkData, setCurrentBookmarkData] = useState(
    {} as BookmarkData
  );
  const [currentBookmarkPage, setCurrentBookmarkPage] = useState(1);

  const fetchAndSetData = useCallback(async (page?: number) => {
    const data = await getBookmarks(page);
    setCurrentBookmarkData(data);
  }, []);
  useEffect(() => {
    fetchAndSetData(currentBookmarkPage);
  }, [currentBookmarkPage]);
  return (
    <div className="list-group">
      {currentBookmarkData.bookmarks?.map((item, index) => {
        return (
          <BookmarkListItem
            key={index}
            title={item.title}
            url={item.url}
            createdAt={item.createdAt}
            updatedAt={item.updatedAt}
          />
        );
      })}
      <BookmarkListFooter
        totalPages={currentBookmarkData.totalPages}
        currentPage={currentBookmarkData.currentPage}
        loadNewPage={setCurrentBookmarkPage}
      />
    </div>
  );
};

export default BookmarkListBody;
